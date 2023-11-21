package yezak.api.api.management_support.attendance.vacation.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class VacationPlanDto {
    private VacationPlanReq vacationPlanReq;
    private List<VacationPlanDetailDto> vacationPlanDetailDtoList;
}
