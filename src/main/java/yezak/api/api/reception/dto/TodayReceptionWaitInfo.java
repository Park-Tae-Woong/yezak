package yezak.api.api.reception.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TodayReceptionWaitInfo {
    @Schema(description = "id")
    private Integer id;
    @Schema(description = "차트번호")
    private String chartNumber;
    @Schema(description = "수진자명")
    private String name;
    @Schema(description = "주민등록번호")
    private String registrationNo;
    @Schema(description = "나이")
    private Integer age;
    @Schema(description = "성별")
    private String gender;
    @Schema(description = "담당의")
    private String doctorName;
    @Schema(description = "상태")
    private String state;
}
