package yezak.api.api.pad.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PadReceptionReq {
    private String name;
    private String phoneNumber;
    private Long hospitalId;
}
