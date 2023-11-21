package yezak.api.api.setting.prescription.fee.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateFeeReq {
    private Long id;
    private String customCode;
    @Hidden
    private Long prescriptionCodeId;
    @Hidden
    private Long hospitalId;
    private String koName;
    private String enName;
    private Integer uninsuredPrice;
    private String applicatedDate;
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
    private Integer estimatedTime;
    private Integer isProduct;

}
