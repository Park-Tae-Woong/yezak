package yezak.api.api.setting.prescription.fee.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertInspectionFeeReq {
    @Hidden
    private Long id;
    private Long subdivisionId;
    @Hidden
    private Long hospitalId;
    @Hidden
    private Long prescriptionCodeId;
    private String koName;
    private String enName;
    private String applicatedDate;
    private String customCode;
    private int uninsuredPrice;
    private Long specimenId;
    private Long examinationType;
    private Long consignmentId;
    private String adultMaleMaximum;
    private String adultMaleMinimum;
    private String adultFemaleMaximum;
    private String adultFemaleMinimum;
    private String childMaximum;
    private String childMinimum;
    private String unit;
}
