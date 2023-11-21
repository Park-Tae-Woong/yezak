package yezak.api.global.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.config.S3Component;
import yezak.api.global.common.AttachmentsDto;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import static yezak.api.config.MyIdConfig.myHospitalId;

@Component
@Service
@Slf4j
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {
    private final AmazonS3Client amazonS3Client;
    private final S3Component s3Component;


    public AttachmentsDto uploadFile(MultipartFile file, String directory) throws FileUploadException {
        String fileName = file.getOriginalFilename();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        //s3 저장
        try (InputStream inputStream = file.getInputStream()) {
            if (file.getSize() <= 15728640) {
                amazonS3Client.putObject(new PutObjectRequest(s3Component.getBucketName() + "/" + directory + "/" + myHospitalId() + "/" + LocalDate.now(), fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead)); //권한 설정
            }
            else {
                throw new RuntimeException("파일 크기가 15MB를 초과합니다.");
            }

        } catch (IOException e) {
            throw new FileUploadException();
        }

        return AttachmentsDto.builder()
                .fileName(fileName)
                .filePath(amazonS3Client.getUrl(s3Component.getBucketName() + "/imageForm/" + myHospitalId() + "/" + LocalDate.now(), fileName).toString())
                .fileExtension(FilenameUtils.getExtension(fileName))
                .fileSize(file.getSize())
                .build();

    }
}
