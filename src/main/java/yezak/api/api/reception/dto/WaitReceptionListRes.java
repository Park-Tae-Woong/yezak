package yezak.api.api.reception.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class WaitReceptionListRes {
    private Long receptionId;
    private Long patientId;
    private String chartNumber;
    private String patientName;
    private String registrationNumber;
    private int age;
    private String sex;
    @JsonFormat(pattern = "hh:mm")
    private LocalDateTime reservatedAt;
    private String doctorName;
    private String isNew;
}
