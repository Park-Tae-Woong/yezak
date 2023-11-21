package yezak.api.api.management_support.evaluation.form.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertFormAndItem {
    private InsertEvaluationFormReq insertEvaluationFormReq;
    private List<InsertEvaluationItemReq> insertEvaluationItemReq;

}
