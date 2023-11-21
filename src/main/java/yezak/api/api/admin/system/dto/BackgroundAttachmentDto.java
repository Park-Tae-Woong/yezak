package yezak.api.api.admin.system.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BackgroundAttachmentDto {
    @Schema(description = "파일 id", example = "1")
    private Long id;
    @Hidden
    @Schema(description = "병원id", example = "1")
    private Long hospitalId;
    @Schema(description = "파일명", example = "스크린샷 2023-04-25 오후 5.25.17.png")
    private String fileName;
    @Schema(description = "파일 저장 주소", example = "yezaktest/스크린샷 2023-04-25 오후 5.25.17.png")
    private String filePath;
    @Schema(description = "확장자", example = "png")
    private String fileExtension;
    @Schema(description = "파일 크기", example = "380741")
    private Long fileSize;
    @Schema(description = "생성 날짜", example = "2023-04-25 00:00:00", hidden = true)
    private LocalDate createdAt;

}
