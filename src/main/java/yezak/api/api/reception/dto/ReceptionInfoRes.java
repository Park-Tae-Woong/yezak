package yezak.api.api.reception.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReceptionInfoRes {
    private Long visitPurposeId;
    private String visitPurposeName;
    private Long doctorId;
    private String doctorName;
    private String protectorName;
    private String protectorPhoneNumber;
    private Long protectorRelationId;
    private String protectorRelationName;
    private String reservatedAt;
    private Long roomId;
    private String roomName;
}
