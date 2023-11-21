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
public class TodayReceptionInfo {
    @Schema(description = "id")
    private Integer id;
    @Schema(description = "차트번호")
    private String chartNumber;
    @Schema(description = "이름")
    private String name;
    @Schema(description = "나이")
    private Integer age;
    @Schema(description = "성별")
    private String gender;
    @JsonFormat(pattern = "hh:mm")
    @Schema(description = "접수시간")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "hh:mm:ss")
    @Schema(description = "예약시간")
    private LocalDateTime reservatedAt;
    @Schema(description = "의사명")
    private String doctorName;
    @Schema(description = "초진/재진 구분")
    private String gubun;
}
