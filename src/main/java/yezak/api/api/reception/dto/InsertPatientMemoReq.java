package yezak.api.api.reception.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class InsertPatientMemoReq {
    @Hidden
    @Schema(description = "로그인한 유저", example = "1")
    private Long userId;
    @Schema(description = "환자id", example = "37")
    private Long patientId;
    @Schema(description = "내용", example = "알레르기 주의")
    private String content;
}
