package yezak.api.api.reception.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReceptionSujinjaSearchResponse {

    @Schema(description = "수진자 자격조회 결과 - 건강보험")
    private List<HealthInsuranceSujinjaInfo> healthInsuranceSujinjaInfoList;

    @Schema(description = "수진자 자격조회 결과 - 의료급여")
    private List<MedicalBenefitsSujinjaInfo> medicalBenefitsSujinjaInfoList;

}
