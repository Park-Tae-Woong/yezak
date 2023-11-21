package yezak.api.api.setting.prescription.fee.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SurgeryFeeDetailRes {
    private String customCode;
    private Long hospitalId;
    private Long categoryId;
    private String categoryName;
    private String subdivisionName;
    private String code;
    private String koName;
    private String enName;
    private int clinicPrice;
    private String payTypeId;
    private String payTypeName;
    private LocalDate applicatedDate;
    private int estimatedTime;
    private int isProduct;
}
