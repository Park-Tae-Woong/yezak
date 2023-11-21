package yezak.api.api.marketing.guest.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageGuestRegistRequest {
    @Schema(description = "제목", example = "내일_23년 1-1차")
    private String title;
    @Hidden
    @Schema(description = "외부등록자id", example = "1")
    private Long userId;
    @Schema(description = "유입경로id", example = "1")
    private Long accessRootId;
    @Hidden
    @Schema(description = "병원id", example = "1")
    private Long hospitalId;
    @Schema(description = "집행금액", example = "100000")
    private Long amount;
    @Schema(description = "집행일자", example = "2023-05-01")
    private LocalDate executionDate;
    @Hidden
    @Schema(description = "등록id", example = "1")
    private Long id;
}
