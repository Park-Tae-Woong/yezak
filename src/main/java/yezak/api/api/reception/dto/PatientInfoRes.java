package yezak.api.api.reception.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PatientInfoRes {
    private String chartNumber;
    private String name;
    private String firstRegistrationNumber;
    private String secondRegistrationNumber;
    private String phoneNumber;
    private String address;
    private String addressDetail;
    private int agreedPersonal;
    private int agreedMarketing;
    private int agreedSettlement;
}
