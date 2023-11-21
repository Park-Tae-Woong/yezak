package yezak.api.api.reception.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReceptionSujinjaSearchRequest {

    @Schema(description = "주민등록번호", example = "1234567891234")
    private String sujinjaJuminNo;

    @Schema(description = "이름", example = "홍길동")
    private String sujinjaJuminNm;

}
