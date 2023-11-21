package yezak.api.api.management_support.approval.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckConfirmStatus {
    private Long firstConfirmStatuses;
    private Long secondConfirmStatuses;
}
