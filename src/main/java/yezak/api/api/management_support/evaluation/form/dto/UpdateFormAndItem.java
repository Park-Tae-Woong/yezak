package yezak.api.api.management_support.evaluation.form.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateFormAndItem {
    private UpdateEvaluationForm updateEvaluationForm;

    private List<UpdateEvaluationItem> updateEvaluationItem;

}
