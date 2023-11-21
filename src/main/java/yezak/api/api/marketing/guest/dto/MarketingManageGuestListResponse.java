package yezak.api.api.marketing.guest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageGuestListResponse {
    @Schema(description = "row id", example = "1")
    private Long id;
    @Schema(description = "제목", example = "내일_23년 1-1차")
    private String title;
    @Schema(description = "외부 db등록한 유저id", example = "1")
    private Long userId;
    @Schema(description = "유입경로id", example = "1")
    private Long accessRootId;
    @Schema(description = "병원id", example = "1")
    private Long hospitalId;
    @Schema(description = "집행금액", example = "100000")
    private Long amount;
    @Schema(description = "집행일자", example = "2023-05-01")
    private LocalDate executionDate;
    @Schema(description = "등록일자", example = "2023-05-01 13:30:00")
    private LocalDateTime createdAt;
    @Schema(description = "수정일자", example = "2023-05-01 13:30:00")
    private LocalDateTime updatedAt;
    @Schema(description = "유입경로명", example = "네이버 블로그 1번광고")
    private String name;
}
