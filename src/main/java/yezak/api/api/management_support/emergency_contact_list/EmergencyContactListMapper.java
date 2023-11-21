package yezak.api.api.management_support.emergency_contact_list;

import org.apache.ibatis.annotations.Mapper;
import yezak.api.api.management_support.emergency_contact_list.dto.EmergencyContactListReq;
import yezak.api.api.management_support.emergency_contact_list.dto.EmergencyContactListRes;

import java.util.List;

@Mapper
public interface EmergencyContactListMapper {
    List<EmergencyContactListRes> emergencyContactList(EmergencyContactListReq emergencyContactListReq);
}
