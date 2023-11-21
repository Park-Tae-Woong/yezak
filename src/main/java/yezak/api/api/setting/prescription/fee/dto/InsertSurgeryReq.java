package yezak.api.api.setting.prescription.fee.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertSurgeryReq {
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
    private int isProduct;
    private Long estimatedTime;
}
