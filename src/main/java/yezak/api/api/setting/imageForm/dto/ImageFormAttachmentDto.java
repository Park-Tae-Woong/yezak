package yezak.api.api.setting.imageForm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class ImageFormAttachmentDto {
    @Schema(description = "파일 id", example = "1")
    private Long id;
    @Schema(description = "서식 id", example = "1")
    private Long imageFormId;
    @Schema(description = "파일명", example = "스크린샷 2023-04-25 오후 5.25.17.png")
    private String fileName;
    @Schema(description = "파일 저장 주소", example = "yezaktest/스크린샷 2023-04-25 오후 5.25.17.png")
    private String filePath;
    @Schema(description = "확장자", example = "png")
    private String fileExtension;
    @Schema(description = "파일 크기", example = "380741")
    private Long fileSize;
}
