package yezak.api.api.setting.prescription.material.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindMaterial {
    private String name;
    private String unit;
    private String specification;
    private String manufacturer;
    private String material;
    private String importer;
    private String applicatedDate;
}
