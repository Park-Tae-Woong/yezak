package yezak.api.api.management_support.evaluation.form;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.management_support.evaluation.form.dto.EvaluationItemAndTotal;
import yezak.api.api.management_support.evaluation.form.dto.InsertFormAndItem;
import yezak.api.api.management_support.evaluation.form.dto.UpdateFormAndItem;

import java.util.List;

@RequestMapping(value = "/api/evaluation")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Evaluation-manage", description = "경영지원 > 인사평가")
public class EvaluationFormController {
    private final EvaluationFormService evaluationFormService;

    @PutMapping(value = "/delete-evaluation")
    @Operation(summary = "평가지 삭제" , description = "평가지 삭제", parameters = {
            @Parameter(name = "ids", description = "평가지 id", example = "1"),
            @Parameter(name = "hospitalIds", description = "병원 id", example = "1")
    })
    public void attendanceRecord(@RequestParam List<Long> ids, @RequestParam List<Long> hospitalIds) {
        evaluationFormService.deleteForm(ids, hospitalIds);
    }

    @GetMapping(value = "/evaluation-list")
    @Operation(summary = "평가지 리스트" , description = "평가지 리스트", parameters = {
            @Parameter(name = "hospitalId", description = "병원 id", example = "1")
    })
    public List<String> formList(@RequestParam Long hospitalId) {
        return evaluationFormService.formList(hospitalId);
    }

    @GetMapping(value = "/evaluation-item-list")
    @Operation(summary = "평가 항목 리스트" , description = "평가 항목 리스트", parameters = {
            @Parameter(name = "evaluationFormId", description = "평가지 id", example = "1")
    })
    public EvaluationItemAndTotal itemList(@RequestParam Long evaluationFormId) {
        return evaluationFormService.itemList(evaluationFormId);
    }

    @PostMapping(value = "/insert-evaluation")
    @Operation(summary = "평가지 신규 등록" , description = "평가지 신규 등록")
    public void itemList(@RequestBody InsertFormAndItem insertFormAndItem) {
        evaluationFormService.insertFormAndItem(insertFormAndItem);
    }

    @PutMapping(value = "/update-evaluation")
    @Operation(summary = "평가지 수정" , description = "평가지 수정")
    public void updateFromAndItem(@RequestBody UpdateFormAndItem updateFormAndItem) {
        evaluationFormService.updateFromAndItem(updateFormAndItem);
    }

    @GetMapping(value = "/evaluation-count")
    @Operation(summary = "평가지 갯수" , description = "평가지 갯수", parameters = {
            @Parameter(name = "hospitalId", description = "병원 id", example = "1")
    })
    public Long countForm(@RequestParam Long hospitalId) {
        return evaluationFormService.countForm(hospitalId);
    }
}
