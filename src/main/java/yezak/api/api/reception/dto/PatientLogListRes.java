package yezak.api.api.reception.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PatientLogListRes {
    private String email;
    private String createdAt;
    private String content;
    private String beforeContent;
}
