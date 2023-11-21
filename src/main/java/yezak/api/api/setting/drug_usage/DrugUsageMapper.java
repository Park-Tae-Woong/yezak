package yezak.api.api.setting.drug_usage;

import org.apache.ibatis.annotations.Mapper;
import yezak.api.api.setting.drug_usage.dto.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface DrugUsageMapper {

    List<DrugUsageListRes> drugUsageList(DrugUsageListReq drugUsageListReq);
    List<Map<String, Objects>> selectUsageType();
    int countDrugUsage(DrugUsageListReq drugUsageListReq);

    void updateUsage(UpdateDrugUsageReq updateDrugUsageReq);

    void insertUsage(InsertDrugUsageReq insertDrugUsageReq);

    Long findByCustomCode(FindByCustomCode findByCustomCode);

}
