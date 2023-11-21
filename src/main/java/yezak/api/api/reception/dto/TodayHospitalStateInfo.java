package yezak.api.api.reception.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TodayHospitalStateInfo {
    @Schema(description = "id")
    private Integer id;
    @Schema(description = "차트번호")
    private String chartNumber;
    @Schema(description = "수진자명")
    private String name;
    @Schema(description = "나이")
    private Integer age;
    @Schema(description = "성별")
    private String gender;
    @JsonFormat(pattern = "hh:mm")
    @Schema(description = "예약시간")
    private LocalDateTime reservatedAt;
    @Schema(description = "대기실")
    private String roomName;
    @Schema(description = "담당의")
    private String doctorName;
    @Schema(description = "상태")
    private String state;
}
