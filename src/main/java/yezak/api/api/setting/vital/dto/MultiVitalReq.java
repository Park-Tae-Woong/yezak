package yezak.api.api.setting.vital.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class MultiVitalReq {
    private List<VitalDto> insertVital;

    private List<UpdateVitalReq> updateVital;

}
