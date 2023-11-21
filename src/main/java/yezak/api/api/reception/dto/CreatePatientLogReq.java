package yezak.api.api.reception.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CreatePatientLogReq {
    private Long userId;
    private Long patientId;
    private Long patientChangeCategoryId;
    private String beforeContent;
}
