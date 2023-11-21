package yezak.api.api.user.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetChangeAmount {
    private LocalDateTime createdAt;
    private Long amount;
}
