package yezak.api.api.setting.vital;

import org.apache.ibatis.annotations.Mapper;
import yezak.api.api.setting.vital.dto.UpdateVitalReq;
import yezak.api.api.setting.vital.dto.VitalDto;
import yezak.api.api.setting.vital.dto.VitalList;

import java.util.List;

@Mapper
public interface VitalMapper {
    List<VitalList> getVitalList(Long hospitalId);

    void insertVital(VitalDto vitalDto);

    void updateVital(UpdateVitalReq updateVitalReq);

}
