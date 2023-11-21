package yezak.api.api.reception.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RecentReceptionRecordRes {
    private Long id;
    private String createdAt;
    private String gubun;
    private String doctorName;
    private String visitPurpose;
}
