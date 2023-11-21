package yezak.api.api.marketing.guest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageGuestFileRequest {
    @Schema(description = "외부DB등록내역id", example = "1")
    private Long patientImportId;
    @Schema(description = "파일명", example = "test.csv")
    private String fileName;
    @Schema(description = "저장경로", example = "/test/test/test")
    private String filePath;
    @Schema(description = "확장자", example = "csv")
    private String fileExtention;
    @Schema(description = "파일크기", example = "1234")
    private Long fileSize;
}
