package yezak.api.api.management_support.approval.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApprovalFileReq {
    private Long askId;
    private String fileName;
    private String filePath;
    private String fileExtension;
    private Long fileSize;
}
