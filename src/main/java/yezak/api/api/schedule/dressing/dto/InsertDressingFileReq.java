package yezak.api.api.schedule.dressing.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertDressingFileReq {
    private Long dressingId;
    private String fileName;
    private String filePath;
    private String fileExtension;
    private Long fileSize;
}
