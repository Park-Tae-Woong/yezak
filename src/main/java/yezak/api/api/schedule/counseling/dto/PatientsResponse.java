package yezak.api.api.schedule.counseling.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Schema(description = "환자 리스트")
public class PatientsResponse {
    @Schema(description = "환자 id")
    private Long id;

    @Schema(description = "차트번호")
    private String chartNumber;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "주민번호")
    private String registrationNo;

    @Schema(description = "핸드폰 번호")
    private String phoneNumber;
}
