package yezak.api.api.setting.prescription.fee.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InspectionCustomFeeDetailRes {
    private String customCode;
    private Long hospitalId;
    private Long categoryId;
    private String categoryName;
    private String subdivisionName;
    private String koName;
    private String enName;
    private int uninsuredPrice;
    private LocalDate applicatedDate;
    private Long specimenId;
    private Long examinationType;
    private Long consignmentId;

}
