package yezak.api.api.reception.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PatientMemoListRes {
    private Long memoId;
    private String name;
    private String createdAt;
    private String content;
}
