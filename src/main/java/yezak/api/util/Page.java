package yezak.api.util;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Page {
    private int pageNum;
    private int pageSize;
    private int totalCount;
    private int totalPage;
}
