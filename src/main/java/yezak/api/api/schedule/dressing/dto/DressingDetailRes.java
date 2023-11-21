package yezak.api.api.schedule.dressing.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DressingDetailRes {
    private String createdAt;
    private String name;
    private String info;
    private String content;
}
