package yezak.api.api.reception.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TodayReceptionReq {
    private Long hospitalId;
    private String gubun;
}
