package yezak.api.api.admin.room.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class UpdateRoomReq {
    @Schema(description = "상위카테고리 id", example = "1")
    private Long scheduleCategoryId;
    @Schema(description = "스케줄 설정", example = "1")
    private int exposeYn;
    @Schema(description = "1일 예약 건수", example = "5")
    private int minutesPerPerson;
    @Schema(description = "1인당 소요시간", example = "5")
    private int maxReservation;
    @Schema(description = "병실명", example = "상담실1")
    private String rKoName;
    @Schema(description = "대기실 id", example = "1")
    private Long id;
    @Hidden
    @Schema(description = "병원 id", example = "1")
    private Long hospitalId;
}
