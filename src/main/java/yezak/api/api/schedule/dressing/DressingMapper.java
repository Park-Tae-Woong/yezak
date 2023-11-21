package yezak.api.api.schedule.dressing;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yezak.api.api.schedule.dressing.dto.*;

import java.util.List;
import java.util.Map;

@Mapper

public interface DressingMapper {
    List<SearchPatientRes> selectPatientsInfo(String searchValue);
    List<GetWaitList> getWaitList(@Param("hospitalId") Long hospitalId, @Param("roomId") Long roomId);
    List<Map<String, Object>> selectRoomList(Long hospitalId);
    OperationBasicInfoRes operationBasicInfo(OperationBasicInfoReq operationBasicInfoReq);
    List<Map<String, Object>> operationBasicInfoList(OperationBasicInfoReq operationBasicInfoReq);
    List<Map<String, Object>> selectOperationDateList(Long patientId);
    List<Map<String, Object>> selectDressingDateList(Long patientId);
    List<Map<String, Object>> patientVital(Long patientId);
    DressingDetailRes dressingDetail(Long dressingId);
    void insertDressing(InsertDressingReq insertDressingReq);
    void insertDressingFile(InsertDressingFileReq insertDressingFileReq);
    void insertDressingUser(InsertDressingUserReq insertDressingUserReq);

}
