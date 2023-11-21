package yezak.api.util;

import org.springframework.web.multipart.MultipartFile;
import yezak.api.global.common.ResultResponse;

public interface ExcelUploadService {
    ResultResponse<?> uploadExcelFile(MultipartFile file);

}
