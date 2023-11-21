package yezak.api.api.setting.prescription.fee.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SurgeryCustomFeeDetailRes {
    private String customCode;
    private Long hospitalId;
    private Long categoryId;
    private String categoryName;
    private String subdivisionName;
    private String koName;
    private String enName;
    private int uninsuredPrice;
    private LocalDate applicatedDate;
    private Long consignmentId;
    private int estimatedTime;
    private int isProduct;
}
