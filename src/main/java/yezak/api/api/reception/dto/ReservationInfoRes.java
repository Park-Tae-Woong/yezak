package yezak.api.api.reception.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReservationInfoRes {
    private String reservatedAt;
    private String roomName;
}
