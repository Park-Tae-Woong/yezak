package yezak.api.api.management_support.meeting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActiveUserDto {
    @Schema(description = "user id", example = "1")
    private Long id;
    @Schema(description = "이름", example = "윤예작")
    private String name;
}
