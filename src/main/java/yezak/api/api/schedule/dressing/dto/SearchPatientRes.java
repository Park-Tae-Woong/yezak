package yezak.api.api.schedule.dressing.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchPatientRes {
    private Long id;
    private String chartNumber;
    private String name;
    private String registrationNumber;
    private String phoneNumber;
}
