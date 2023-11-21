package yezak.api.api.reception.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UpdateReceptionInfoReq {
    private Long visitPurposeId;
    private Long doctorId;
    private String protectorName;
    private String protectorPhoneNumber;
    private Long protectorRelationId;
    private Long roomId;
    private Long statusId;
    private Long receptionId;
}
