package yezak.api.api.setting.imageForm.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class ImageFormFilter {
    private Long hospitalId;
    private String searchValue;
}
