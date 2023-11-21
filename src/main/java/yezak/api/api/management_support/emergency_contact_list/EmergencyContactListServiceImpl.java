package yezak.api.api.management_support.emergency_contact_list;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import yezak.api.api.management_support.emergency_contact_list.dto.EmergencyContactListReq;
import yezak.api.api.management_support.emergency_contact_list.dto.EmergencyContactListRes;
import yezak.api.global.common.ResultResponse;

import java.util.List;

import static yezak.api.config.MyIdConfig.myDepth3Id;
import static yezak.api.config.MyIdConfig.myHospitalId;

@RequiredArgsConstructor
@Component
@Service
@Slf4j
public class EmergencyContactListServiceImpl implements EmergencyContactListService{

    private final EmergencyContactListMapper emergencyContactListMapper;

    @Override
    public ResultResponse<?> emergencyContactList(Long roleCategoryId, Long employmentTypeId, String searchValue) {
        Integer emergencyContactListId = 33;

        if(myDepth3Id().contains(emergencyContactListId)) {
            EmergencyContactListReq emergencyContactListReq = new EmergencyContactListReq(roleCategoryId, employmentTypeId, searchValue, myHospitalId());
            List<EmergencyContactListRes> emergencyContactListRes = emergencyContactListMapper.emergencyContactList(emergencyContactListReq);
            return ResultResponse.builder()
                    .data(emergencyContactListRes)
                    .result(true)
                    .resultCode("200")
                    .resultMessage("성공")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("400")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }
}
