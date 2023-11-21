package yezak.api.global.service;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.global.common.AttachmentsDto;

public interface S3Service {

    AttachmentsDto uploadFile(MultipartFile file, String directory) throws FileUploadException;

}
