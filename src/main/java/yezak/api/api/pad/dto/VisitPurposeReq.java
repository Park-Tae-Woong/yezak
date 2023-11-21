package yezak.api.api.pad.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitPurposeReq {
    private Long patientId;
    private Long visitPurposeId;
}
