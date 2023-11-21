package yezak.api.api.setting.prescription.drug.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindCodeAndHosByDrugIdRes {
    private Long drugCodeId;
    private Long hospitalId;
}
