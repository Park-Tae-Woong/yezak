package yezak.api.api.setting.prescription.fee.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindCustomCodeReq {
    private String customCode;
    private Long hospitalId;
}
