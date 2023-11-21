package yezak.api.api.setting.disease.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomCodeDuplicateCheck {
    private String customCode;
    private Long hospitalId;
}
