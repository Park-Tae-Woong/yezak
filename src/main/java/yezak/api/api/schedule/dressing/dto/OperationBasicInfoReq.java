package yezak.api.api.schedule.dressing.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationBasicInfoReq {
    private Long patientId;
    private Long operationId;
    private Long hospitalId;
    private String date;
}
