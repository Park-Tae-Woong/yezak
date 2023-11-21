package yezak.api.api.admin.hospital.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitPurposeMulti {
    private List<InsertVisitPurposeReq> insertVisitPurposeReqs;
    private List<UpdateVisitPurposeReq> updateVisitPurposeReqs;
    private List<DeleteVisitPurposeReq> deleteVisitPurposeReqs;
}
