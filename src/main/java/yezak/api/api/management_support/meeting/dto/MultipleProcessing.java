package yezak.api.api.management_support.meeting.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MultipleProcessing {
    private UpdateContentReq updateContentReq;
    private List<UpdateConclusionReq> updateConclusionReqs;
    private List<InsertConclusionReq> insertConclusionReqs;
    private List<Long> deleteConclusionReqs;

}
