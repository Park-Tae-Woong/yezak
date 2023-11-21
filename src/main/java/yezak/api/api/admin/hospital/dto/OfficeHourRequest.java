package yezak.api.api.admin.hospital.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficeHourRequest {
    @Schema(description = "업무시간 정보")
    private List<OfficeHourReq> officeHour;
    @Schema(description = "법정공휴일 휴무 여부(Y:1 / N:0)", example = "1")
    private Integer holidayRestYn;
}
