package yezak.api.api.setting.disease.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertDiseaseOptionReq {
    private Long diseaseId;
    private Long hospitalId;
    private String customCode;

}
