package yezak.api.api.management_support.inventory.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryLogListRes {
    private String category;
    private String controlledAt;
    private String userName;
    private String code;
    private String name;
    private String specification;
    private String unit;
    private String manufacturer;
    private String material;
    private String importer;
    private Integer beforeAmount;
    private Integer afterAmount;
    private String remark;
}
