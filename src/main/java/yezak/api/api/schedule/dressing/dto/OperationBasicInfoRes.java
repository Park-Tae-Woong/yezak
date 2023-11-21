package yezak.api.api.schedule.dressing.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationBasicInfoRes {
    private String date;
    private String doctor;
    private String nurse;
}
