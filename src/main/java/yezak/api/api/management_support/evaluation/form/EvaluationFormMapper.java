package yezak.api.api.management_support.evaluation.form;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yezak.api.api.management_support.evaluation.form.dto.*;

import java.util.List;

@Mapper
public interface EvaluationFormMapper {

    void deleteForm(@Param("id") Long id, @Param("hospitalId") Long hospitalId);
    List<String> formList(Long hospitalId);
    List<EvaluationItemList> itemList(Long evaluationFormId);
    int totalValue(Long evaluationFormId);
    void insertForm (InsertEvaluationFormReq insertEvaluationFormReq);
    void insertItem (InsertEvaluationItemReq insertEvaluationItemReq);
    void updateForm (UpdateEvaluationForm updateEvaluationForm);
    void updateItem (UpdateEvaluationItem updateEvaluationItem);
    Long countForm (Long hospitalId);
}
