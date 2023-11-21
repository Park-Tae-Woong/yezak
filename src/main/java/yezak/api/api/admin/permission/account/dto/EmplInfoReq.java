package yezak.api.api.admin.permission.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmplInfoReq {
    @Schema(description = "고용형태", example = "1")
    private Long employmentTypeId;
    @Schema(description = "진료과", example = "1")
    private Long treatmentSubjectId;
    @Schema(description = "직군id", example = "1")
    private Long roleId;
    @Schema(description = "직군카테고리id", example = "1")
    private Long roleCategoryId;
    @Schema(description = "입사일시", example = "2022-02-02")
    private LocalDate joinedAt;
    @Schema(description = "수습여부", example = "1")
    private int internshipYn;
    @Schema(description = "수습 시작일자", example = "2022-02-02")
    private LocalDate internshipStart;
    @Schema(description = "수습 종료일자", example = "2022-02-05")
    private LocalDate internshipEnd;
    @Schema(description = "근로상태", example = "1")
    private Long employmentStatusId;
    @Schema(description = "휴직 시작일자", example = "2022-02-02")
    private LocalDate restStart;
    @Schema(description = "휴직 종료일자", example = "2022-02-03")
    private LocalDate restEnd;
    @Schema(description = "퇴직연급가입일", example = "2022-02-02")
    private LocalDate pensionDate;
    @Schema(description = "퇴사일자", example = "2022-02-02")
    private LocalDate leavedAt;
    @Schema(description = "퇴사사유", example = "개인사유")
    private String leaveReason;
    @Schema(description = "유저id", example = "1")
    private Long id;

}
