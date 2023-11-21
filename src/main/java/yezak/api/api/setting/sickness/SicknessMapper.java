package yezak.api.api.setting.sickness;

import org.apache.ibatis.annotations.Mapper;
import yezak.api.api.setting.sickness.dto.CreateSicknessReq;
import yezak.api.api.setting.sickness.dto.SicknessDto;
import yezak.api.api.setting.sickness.dto.SicknessListReq;
import yezak.api.api.setting.sickness.dto.UpdateSicknessReq;

import java.util.List;

@Mapper
public interface SicknessMapper {

    List<SicknessDto> getSickList(SicknessListReq sicknessListReq);

    void insertSick(CreateSicknessReq createSicknessReq);

    void updateSick(UpdateSicknessReq updateSicknessReq);

    String findByCode (String customCode);

    void deleteSick(Long id);


}
