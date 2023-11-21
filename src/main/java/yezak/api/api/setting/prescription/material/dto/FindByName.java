package yezak.api.api.setting.prescription.material.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindByName {
    private String name;
    private Long hospitalId;

}
