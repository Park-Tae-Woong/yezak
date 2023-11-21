package yezak.api.global.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class AttachmentsDto {
    @Schema(description = "파일 id", example = "1")
    private Long id;

    @Schema(description = "기존 테이블 id(counseling_id, message_id, hq_board_id 등)", example = "1")
    private Long originalTblId;

    @Schema(description = "파일명", example = "스크린샷 2023-04-25 오후 5.25.17.png")
    private String fileName;

    @Schema(description = "파일 저장 주소", example = "yezaktest/스크린샷 2023-04-25 오후 5.25.17.png")
    private String filePath;

    @Schema(description = "확장자", example = "png")
    private String fileExtension;

    @Schema(description = "파일 크기", example = "380741")
    private Long fileSize;
}
