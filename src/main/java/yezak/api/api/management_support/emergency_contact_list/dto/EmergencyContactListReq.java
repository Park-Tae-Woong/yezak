package yezak.api.api.management_support.emergency_contact_list.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmergencyContactListReq {
    @Schema(description = "직군카테고리id", example = "1")
    private Long roleCategoryId;
    @Schema(description = "고용형태 id", example = "1")
    private Long employmentTypeId;
    @Schema(description = "검색어", example = "홍길동")
    private String searchValue;
    @Schema(description = "병원id", example = "1", hidden = true)
    private Long hospitalId;

}
