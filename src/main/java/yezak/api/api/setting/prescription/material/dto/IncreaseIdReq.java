package yezak.api.api.setting.prescription.material.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncreaseIdReq {
    private Long id;
    private Long hospitalId;
    private String code;
}
