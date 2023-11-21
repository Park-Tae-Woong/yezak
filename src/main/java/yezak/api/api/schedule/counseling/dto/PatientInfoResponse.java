package yezak.api.api.schedule.counseling.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "환자 정보 조회")
public class PatientInfoResponse {
    private NameInheritFactor nameInheritFactor;

    private List<VitalSignColumns> vitalSignColumns;

    private List<PatientVitalSignsResponse> todaysVital;
}
