package yezak.api.api.setting.prescription.material.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindCode {
    private String code;
    private Long hospitalId;
}
