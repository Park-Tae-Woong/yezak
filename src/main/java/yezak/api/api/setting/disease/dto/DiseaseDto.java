package yezak.api.api.setting.disease.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiseaseDto {
    private Long id;
    private Long diseaseOptionId;
    @Schema(description = "사용자코드", example = "a00")
    private String customCode;
    @Schema(description = "상병기호", example = "A00")
    private String diseaseCode;
    @Schema(description = "한글명칭", example = "콜레라")
    private String koName;
    @Schema(description = "영문명칭", example = "Cholera")
    private String enName;
    @Schema(description = "성별", example = "구분없음")
    private String genderClassification;
    @Schema(description = "완전구분", example = "불완전")
    private String perfectCodeChecker;
    @Schema(description = "주상병 사용 구분", example = "사용가능")
    private String mainSickness;
    @Schema(description = "법정감염병", example = "제 2급")
    private String legalInfectiousDisease;

}
