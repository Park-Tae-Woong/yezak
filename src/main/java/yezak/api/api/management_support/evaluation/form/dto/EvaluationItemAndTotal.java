package yezak.api.api.management_support.evaluation.form.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationItemAndTotal {
    private List<EvaluationItemList> itemList;
    private int totalValue;
}
