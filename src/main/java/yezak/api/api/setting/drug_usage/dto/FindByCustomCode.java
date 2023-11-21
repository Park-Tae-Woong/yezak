package yezak.api.api.setting.drug_usage.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindByCustomCode {
    private Long hospitalId;
    private String customCode;
}
