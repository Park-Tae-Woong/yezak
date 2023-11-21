package yezak.api.api.schedule.dressing;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.api.schedule.dressing.dto.InsertDressingReq;
import yezak.api.api.schedule.dressing.dto.InsertDressingUserReq;
import yezak.api.global.common.ResultResponse;

import java.util.List;

public interface DressingService {
    ResultResponse selectPatientsInfo(String searchValue);
    ResultResponse getWaitList(Long roomId);
    ResultResponse selectRoomList();
    ResultResponse operationBasicInfo(Long patientId, Long operationId, String date);
    ResultResponse selectOperationDateList(Long patientId);
    ResultResponse selectDressingDateList(Long patientId);
    ResultResponse patientVital(Long patientId);
    ResultResponse dressingDetail(Long dressingId);
    ResultResponse insertDressing(InsertDressingReq insertDressingReq,
                                  List<InsertDressingUserReq> insertDressingUserReqs,
                                  MultipartFile file0,
                                  MultipartFile file1,
                                  MultipartFile file2,
                                  MultipartFile file3,
                                  MultipartFile file4)throws FileUploadException;

}
