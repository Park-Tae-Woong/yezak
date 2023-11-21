package yezak.api.api.pad.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewPatientReq {
    private Long id;
    private String name;
    private String phoneNumber;
    private String firstRegistrationNumber;
    private String secondRegistrationNumber;
    private String address;
    private String addressDetail;
    private int agreedPersonal;
    private int agreedMarketing;
    private int agreedSettlement;
}
