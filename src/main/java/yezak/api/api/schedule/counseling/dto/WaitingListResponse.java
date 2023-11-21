package yezak.api.api.schedule.counseling.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "대기 리스트")
public class WaitingListResponse {
    List<WaitingResponse> waitingList;

    List<StatusListResponse> statusList;
}
