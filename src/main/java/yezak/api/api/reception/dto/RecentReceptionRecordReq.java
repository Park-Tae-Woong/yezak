package yezak.api.api.reception.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RecentReceptionRecordReq {
    private String start;
    private String end;
    private Long hospitalId;
    private Long patientId;
}
