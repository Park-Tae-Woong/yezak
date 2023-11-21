package yezak.api.api.management_support.attendance.vacation.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class UpdateVacationPlanDto {
    private List<UpdateVacationPlanDetailReq> updateVacationPlanDetailReqList;
    private List<Long> deleteIds;
}
