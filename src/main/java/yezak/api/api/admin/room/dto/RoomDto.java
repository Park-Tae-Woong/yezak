package yezak.api.api.admin.room.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class RoomDto {
    @Schema(description = "번호", example = "1")
    private int no;
    @Schema(description = "대기실id", example = "1")
    private Long id;
    @Schema(description = "상위 카테고리id", example = "1")
    private Long scheduleCategoryId;
    @Schema(description = "상위 카테고리", example = "대면상담실")
    private String scKoName;
    @Schema(description = "병실명", example = "상담실1")
    private String rKoName;
    @Schema(description = "스케줄설정", example = "1")
    private int exposeYn;
    @Schema(description = "평균소요시간", example = "5분")
    private int minutesPerPerson;
    @Schema(description = "예약 가능 건수", example = "30분")
    private int maxReservation;
}
