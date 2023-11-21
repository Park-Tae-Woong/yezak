package yezak.api.api.setting.prescription.drug.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindCustomCode {
    private Long hospitalId;
    private String customCode;
}
