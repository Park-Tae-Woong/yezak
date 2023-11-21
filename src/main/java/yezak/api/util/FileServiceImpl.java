package yezak.api.util;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.api.schedule.dressing.dto.InsertDressingFileReq;
import yezak.api.config.S3Component;
import yezak.api.api.management_support.approval.dto.ApprovalFileReq;
import yezak.api.api.setting.imageForm.dto.ImageFormAttachmentDto;
import yezak.api.api.management_support.meeting.dto.MeetingAttachmentDto;
import yezak.api.api.admin.system.dto.BackgroundAttachmentDto;
import yezak.api.global.common.ResultResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static yezak.api.config.MyIdConfig.myHospitalId;

@Component
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class FileServiceImpl implements FileService {

    private final AmazonS3Client amazonS3Client;
    private final S3Component s3Component;


    public ImageFormAttachmentDto saveImageFile(MultipartFile file) throws FileUploadException {
        String fileName = file.getOriginalFilename();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        //s3 저장
        try (InputStream inputStream = file.getInputStream()) {
            if (file.getSize() <= 15728640) {
                amazonS3Client.putObject(new PutObjectRequest(s3Component.getBucketName() + "/imageForm/" + myHospitalId() + "/" + LocalDate.now(), fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead)); //권한 설정
            }
            else {
                throw new RuntimeException("파일 크기가 15MB를 초과합니다.");
            }

        } catch (IOException e) {
            throw new FileUploadException();
        }

        return ImageFormAttachmentDto.builder()
                .fileName(fileName)
                .filePath(amazonS3Client.getUrl(s3Component.getBucketName() + "/imageForm/" + myHospitalId() + "/" + LocalDate.now(), fileName).toString())
                .fileExtension(FilenameUtils.getExtension(fileName))
                .fileSize(file.getSize())
                .build();

    }

    public MeetingAttachmentDto saveMeetingFile(MultipartFile file) throws FileUploadException {
        String fileName = file.getOriginalFilename();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        //s3 저장
        try (InputStream inputStream = file.getInputStream()) {
            if (file.getSize() <= 15728640) {
                amazonS3Client.putObject(new PutObjectRequest(s3Component.getBucketName() + "/meeting/" + myHospitalId() + "/" + LocalDate.now(), fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead)); //권한 설정
            } else {
                throw new RuntimeException("파일 크기가 15MB를 초과합니다.");
            }
        } catch (IOException e) {
            throw new FileUploadException();
        }

        String filePath = null;
        if (!file.isEmpty()) {
            filePath = amazonS3Client.getUrl(s3Component.getBucketName() + "/meeting/" + myHospitalId() + "/" + LocalDate.now(), fileName).toString();
        }
        String decodeFilePath = URLDecoder.decode(filePath, StandardCharsets.UTF_8);

        //dto에 정보 저장 후 리턴
        return MeetingAttachmentDto.builder()
                .fileName(fileName)
                .filePath(decodeFilePath)
                .fileExtension(FilenameUtils.getExtension(fileName))
                .fileSize(file.getSize())
                .build();
    }

    @Override
    public BackgroundAttachmentDto saveBackgroundFile(MultipartFile file) throws FileUploadException {
        String fileName = file.getOriginalFilename();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        //s3 저장
        try (InputStream inputStream = file.getInputStream()) {
            if (file.getSize() <= 15728640) {
                amazonS3Client.putObject(new PutObjectRequest(s3Component.getBucketName() + "/system/" + myHospitalId() + "/" + LocalDate.now(), fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead)); //권한 설정
            } else {
                throw new RuntimeException("파일 크기가 15MB를 초과합니다.");
            }
        } catch (IOException e) {
            throw new FileUploadException();
        }

        //dto에 정보 저장 후 리턴
        return BackgroundAttachmentDto.builder()
                .fileName(fileName)
                .filePath(amazonS3Client.getUrl(s3Component.getBucketName() + "/system/" + myHospitalId() + "/" + LocalDate.now(), fileName).toString())
                .fileExtension(FilenameUtils.getExtension(fileName))
                .fileSize(file.getSize())
                .createdAt(LocalDate.now())
                .build();
    }

    @Override
    public ResultResponse<?> getMarketingExcelFile() {
        String fileName = "[정책서]_마케팅상담 GNB.xlsx";
        return ResultResponse.builder()
                .data(amazonS3Client.getUrl(s3Component.getBucketName() + "/marketing", fileName).toString())
                .result(true)
                .resultCode("200")
                .build();
    }

    @Override
    public ApprovalFileReq createApprovalFile(MultipartFile file) throws FileUploadException {
        String fileName = file.getOriginalFilename(); // 한글
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        //s3 저장
        try (InputStream inputStream = file.getInputStream()) {
            if (file.getSize() <= 15728640) {
                amazonS3Client.putObject(new PutObjectRequest(s3Component.getBucketName() + "/approval/" + myHospitalId() + "/" + LocalDate.now(), fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead)); //권한 설정
            } else {
                throw new RuntimeException("파일 크기가 15MB를 초과합니다.");
            }
        } catch (IOException e) {
            throw new FileUploadException();
        }

        //dto에 정보 저장 후 리턴
        return ApprovalFileReq.builder()
                .fileName(fileName)
                .filePath(amazonS3Client.getUrl(s3Component.getBucketName() + "/approval/" + myHospitalId() + "/" + LocalDate.now(), fileName).toString()) //#$%$%@$
                .fileExtension(FilenameUtils.getExtension(fileName))
                .fileSize(file.getSize())
                .build();
    }

    @Override
    public ResultResponse deleteFile(String filePath) {
        if (filePath == null) {
            return ResultResponse.builder()
                    .resultCode("400")
                    .result(false)
                    .resultMessage("삭제할 파일이 없습니다.")
                    .build();
        }
        String splitStr = "yezak/";
        String fileName = filePath.substring(filePath.lastIndexOf(splitStr) + splitStr.length()); // meeting/15/2023-09-15/KakaoTalk_Photo_2023-07-25-10-17-13.jpeg

        try {
            amazonS3Client.deleteObject(s3Component.getBucketName(), fileName);
        } catch (SdkClientException e){
            e.printStackTrace();
        }
        return ResultResponse.builder()
                .resultCode("200")
                .result(true)
                .resultMessage("삭제되었습니다.")
                .build();
    }

    @Override
    public InsertDressingFileReq insertDressingFile(MultipartFile file) throws FileUploadException {
        String fileName = file.getOriginalFilename();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        //s3 저장
        try (InputStream inputStream = file.getInputStream()) {
            if (file.getSize() <= 15728640) {
                amazonS3Client.putObject(new PutObjectRequest(s3Component.getBucketName() + "/dressing/" + myHospitalId() + "/" + LocalDate.now(), fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead)); //권한 설정
            } else {
                throw new RuntimeException("파일 크기가 15MB를 초과합니다.");
            }
        } catch (IOException e) {
            throw new FileUploadException();
        }

        String filePath = null;
        if (!file.isEmpty()) {
            filePath = amazonS3Client.getUrl(s3Component.getBucketName() + "/dressing/" + myHospitalId() + "/" + LocalDate.now(), fileName).toString();
        }
        String decodeFilePath = URLDecoder.decode(filePath, StandardCharsets.UTF_8);

        //dto에 정보 저장 후 리턴
        return InsertDressingFileReq.builder()
                .fileName(fileName)
                .filePath(decodeFilePath)
                .fileExtension(FilenameUtils.getExtension(fileName))
                .fileSize(file.getSize())
                .build();

    }
}
