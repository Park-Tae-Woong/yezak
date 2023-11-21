package yezak.api.api.setting.imageForm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class ImageFormRes {
    @Schema(description = "서식id", example = "1")
    private Long id;
    @Schema(description = "서식명", example = "사전설문지")
    private String name;
    @Schema(description = "등록일", example = "2022.10.10")
    private LocalDate createdAt;
    @Schema(description = "파일path", example = "yezak.png")
    private String filePath;
}
