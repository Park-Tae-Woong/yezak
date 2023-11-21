package yezak.api.api.reception.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class HealthInsuranceSujinjaInfo {
    @Schema(description = "수진자 주민등록번호", example = "0001013123454")
    private String sujinjaJuminNo;
    @Schema(description = "수진자 이름", example = "홍길동")
    private String sujinjaJuminNm;
    @Schema(description = "의료급여기관번호", example = "41300033")
    private String ykiho;
    @Schema(description = "자격여부", example = "5")
    private String qlfType;
    @Schema(description = "자격취득일", example = "20070119")
    private String qlfChwidukDt;
    @Schema(description = "세대주 성명", example = "홍길부")
    private String sedaejuNm;
    @Schema(description = "사업장 기호", example = "3510000")
    private String protAdminSym;
    @Schema(description = "건강보험 증번호", example = "80962768156")
    private String asylmSym;
    @Schema(description = "건강보험 상실일", example = "20071231")
    private String payRestricDt;
    @Schema(description = "사용안함/공란")
    private String sbrdnType;
    @Schema(description = "사용안함/공란")
    private Long cfhcRem;
    @Schema(description = "출국자 여부(Y/N)", example = "N")
    private String dprtYn;
    @Schema(description = "사용안함/공란")
    private String obstRegDt;
    @Schema(description = "관할지사 코드", example = "4130XXXX")
    private String ykiho1;
    @Schema(description = "사용안함/공란")
    private String ykiho2;
    @Schema(description = "사용안함/공란")
    private String ykiho3;
    @Schema(description = "사용안함/공란")
    private String ykiho4;
    @Schema(description = "관할지사 명칭", example = "A의원")
    private String yoyangNm1;
    @Schema(description = "사용안함/공란")
    private String yoyangNm2;
    @Schema(description = "사용안함/공란")
    private String yoyangNm3;
    @Schema(description = "사용안함/공란")
    private String yoyangNm4;
    @Schema(description = "일자")
    private String date;
    @Schema(description = "메세지 코드", example = "IWS10001")
    private String messageCode;
    @Schema(description = "메세지", example = "현재일자의 자격정보입니다.")
    private String message;
    @Schema(description = "조작자 주민번호")
    private String operatorJuminNo;
    @Schema(description = "메세지 타입(M2 고정)", example = "M2")
    private String msgType;
    @Schema(description = "클라이언트 정보", example = "14.52.247.42:13884")
    private String clientInfo;
}
