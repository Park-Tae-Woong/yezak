package yezak.api.api.schedule.counseling.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "바이탈 정보 과거이력")
public class VitalHistoryListResponse {

    @JsonFormat(pattern="yyyy.MM.dd")
    private String createdAt;

    private List<PatientVitalSignsResponse> historyList;
}
