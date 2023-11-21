package yezak.api.api.setting.prescription.material.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MuiltiInsert {
    private InsertMaterialOption insertMaterialOption;
    private InsertMaterial insertMaterial;
}
