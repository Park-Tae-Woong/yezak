package yezak.api.api.reception.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReceptionPatientInfo {
    @Schema(description = "id")
    private Integer id;
    @Schema(description = "차트번호")
    private String chartNumber;
    @Schema(description = "이름")
    private String name;
    @Schema(description = "주민번호")
    private String registrationNumber;
    @Schema(description = "연락처")
    private String phoneNumber;
}
