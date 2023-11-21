package yezak.api.api.management_support.emergency_contact_list;

import yezak.api.global.common.ResultResponse;

public interface EmergencyContactListService {
    ResultResponse<?> emergencyContactList(Long roleCategoryId, Long employmentTypeId, String searchValue);

}
