package yezak.api.api.setting.prescription.drug.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindDrugCode {
    private String code;
    private Long hospitalId;
}
