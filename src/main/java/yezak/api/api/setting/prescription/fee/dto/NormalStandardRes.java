package yezak.api.api.setting.prescription.fee.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NormalStandardRes {
    private String adultMaleMinimum;
    private String adultMaleMaximum;
    private String adultFemaleMinimum;
    private String adultFemaleMaximum;
    private String childMinimum;
    private String childMaximum;
    private String unit;
}
