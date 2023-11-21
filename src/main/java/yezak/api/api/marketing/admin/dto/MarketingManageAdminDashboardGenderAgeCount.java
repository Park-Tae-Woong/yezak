package yezak.api.api.marketing.admin.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageAdminDashboardGenderAgeCount {
//    @Schema(description = "0~14 남자 수", example = "1")
//    private Integer man0to14;
//    @Schema(description = "0~14 여자 수", example = "1")
//    private Integer woman0to14;
//
//    @Schema(description = "15~24 남자 수", example = "1")
//    private Integer man15to24;
//    @Schema(description = "15~24 여자 수", example = "1")
//    private Integer woman15to24;
//
//    @Schema(description = "25~34 남자 수", example = "1")
//    private Integer man25to34;
//    @Schema(description = "25~34 여자 수", example = "1")
//    private Integer woman25to34;
//
//    @Schema(description = "35~44 남자 수", example = "1")
//    private Integer man35to44;
//    @Schema(description = "35~44 여자 수", example = "1")
//    private Integer woman35to44;
//
//    @Schema(description = "45~54 남자 수", example = "1")
//    private Integer man45to54;
//    @Schema(description = "45~54 여자 수", example = "1")
//    private Integer woman45to54;
//
//    @Schema(description = "55~64 남자 수", example = "1")
//    private Integer man55to64;
//    @Schema(description = "55~64 여자 수", example = "1")
//    private Integer woman55to64;
//
//    @Schema(description = "65~ 남자 수", example = "1")
//    private Integer man65over;
//    @Schema(description = "65~ 여자 수", example = "1")
//    private Integer woman65over;

    private List<Integer> man;
    private List<Integer> woman;

}
