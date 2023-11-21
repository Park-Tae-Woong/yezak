package yezak.api.api.management_support.meeting.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConclusionFileListRes {
    private Long fileId;
    private String fileName;
    private String filePath;

}
