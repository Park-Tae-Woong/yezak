package yezak.api.api.management_support.evaluation.form;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import yezak.api.api.management_support.evaluation.form.dto.*;

import java.util.List;

@RequiredArgsConstructor
@Component
@Service
@Slf4j
public class EvaluationFormServiceImpl implements EvaluationFormService {
    private final EvaluationFormMapper evaluationFormMapper;
    private final int evaluationFormId = 36;

    @Override
    public void deleteForm(List<Long> ids, List<Long> hospitalIds) {

        if (ids.size() != hospitalIds.size()) {
            throw new IllegalArgumentException("id와 병원id 갯수가 다릅니다.");
        }
        for (int i = 0; i < ids.size(); i++){
            Long id = ids.get(i);
            Long hospitalId = hospitalIds.get(i);

            evaluationFormMapper.deleteForm(id, hospitalId);
        }
    }

    @Override
    public List<String> formList(Long hospitalId) {
        return evaluationFormMapper.formList(hospitalId);
    }

    @Override
    public EvaluationItemAndTotal itemList(Long evaluationFormId) {
        List<EvaluationItemList> evaluationItemLists = evaluationFormMapper.itemList(evaluationFormId);
        int totalValue = evaluationFormMapper.totalValue(evaluationFormId);
        return EvaluationItemAndTotal.builder()
                .itemList(evaluationItemLists)
                .totalValue(totalValue)
                .build();
    }

    @Override
    public void insertFormAndItem(InsertFormAndItem insertFormAndItem) {
        evaluationFormMapper.insertForm(insertFormAndItem.getInsertEvaluationFormReq());
        Long id = insertFormAndItem.getInsertEvaluationFormReq().getId();
        for (InsertEvaluationItemReq insertEvaluationItemReq : insertFormAndItem.getInsertEvaluationItemReq()){
            insertEvaluationItemReq.setEvaluationFormId(id);
            evaluationFormMapper.insertItem(insertEvaluationItemReq);
        }

    }

    @Override
    public void updateFromAndItem(UpdateFormAndItem updateFormAndItem) {
        evaluationFormMapper.updateForm(updateFormAndItem.getUpdateEvaluationForm());
        for (UpdateEvaluationItem updateEvaluationItem : updateFormAndItem.getUpdateEvaluationItem()){
            updateEvaluationItem.setEvaluationFormId(updateFormAndItem.getUpdateEvaluationForm().getId());
            evaluationFormMapper.updateItem(updateEvaluationItem);
        }
    }

    @Override
    public Long countForm(Long hospitalId) {
        return evaluationFormMapper.countForm(hospitalId);
    }
}
