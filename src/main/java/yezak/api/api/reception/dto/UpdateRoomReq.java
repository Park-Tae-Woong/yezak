package yezak.api.api.reception.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UpdateRoomReq {
    private Long roomId;
    private Long id;
}
