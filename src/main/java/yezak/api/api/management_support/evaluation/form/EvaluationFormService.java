package yezak.api.api.management_support.evaluation.form;

import yezak.api.api.management_support.evaluation.form.dto.EvaluationItemAndTotal;
import yezak.api.api.management_support.evaluation.form.dto.InsertFormAndItem;
import yezak.api.api.management_support.evaluation.form.dto.UpdateFormAndItem;

import java.util.List;

public interface EvaluationFormService {
    void deleteForm(List<Long> ids, List<Long> hospitalIds);
    List<String> formList(Long hospitalId);
    EvaluationItemAndTotal itemList(Long evaluationFormId);

    void insertFormAndItem (InsertFormAndItem insertFormAndItem);

    void updateFromAndItem (UpdateFormAndItem updateFormAndItem);
    Long countForm (Long hospitalId);

}
