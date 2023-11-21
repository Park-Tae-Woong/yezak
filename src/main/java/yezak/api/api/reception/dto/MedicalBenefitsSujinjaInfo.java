package yezak.api.api.reception.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MedicalBenefitsSujinjaInfo {
    @Schema(description = "수진자 주민등록번호", example = "0001013123454")
    private String sujinjaJuminNo;
    @Schema(description = "수진자 이름", example = "홍길동")
    private String sujinjaJuminNm;
    @Schema(description = "의료급여기관번호", example = "41300033")
    private String ykiho;
    @Schema(description = "자격여부", example = "7")
    private String qlfType;
    @Schema(description = "자격취득일", example = "20070119")
    private String qlfChwidukDt;
    @Schema(description = "세대주 성명", example = "홍길부")
    private String sedaejuNm;
    @Schema(description = "보장기관 기호", example = "4680064")
    private String protAdminSym;
    @Schema(description = "시설기호", example = "1219")
    private String asylmSym;
    @Schema(description = "급여제한일자", example = "20071231")
    private String payRestricDt;
    @Schema(description = "본인부담여부", example = "M001")
    private String sbrdnType;
    @Schema(description = "건강생활유지비잔액", example = "12000")
    private Long cfhcRem;
    @Schema(description = "출국자 여부(Y/N)", example = "N")
    private String dprtYn;
    @Schema(description = "장애인 등록일자", example = "20071231")
    private String obstRegDt;
    @Schema(description = "선택기관기호1", example = "4130xxxx")
    private String ykiho1;
    @Schema(description = "선택기관기호2", example = "4130xxxx")
    private String ykiho2;
    @Schema(description = "선택기관기호3", example = "4130xxxx")
    private String ykiho3;
    @Schema(description = "선택기관기호4", example = "4130xxxx")
    private String ykiho4;
    @Schema(description = "선택기관 이름1", example = "A의원")
    private String yoyangNm1;
    @Schema(description = "선택기관 이름2", example = "B의원")
    private String yoyangNm2;
    @Schema(description = "선택기관 이름3", example = "C의원")
    private String yoyangNm3;
    @Schema(description = "선택기관 이름4", example = "D의원")
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
