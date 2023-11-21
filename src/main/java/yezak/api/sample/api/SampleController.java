package yezak.api.sample.api;

import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;
import yezak.api.sample.domain.Sample;
import yezak.api.sample.dto.SampleListResponse;
import yezak.api.sample.dto.SampleSaveRequest;
import yezak.api.sample.dto.SampleViewResponse;
import yezak.api.sample.service.SampleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/manager")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "SAMPLE", description = "SAMPLE 컨텐츠 조회, 추가, 수정, 삭제")
public class SampleController {

	private final SampleService sampleService;

	@GetMapping(value = "/index")
	public String index() {
		log.info("SampleController - index");

		return "index";
	}

	@GetMapping(value = "/sample")
	@Operation(summary = "SAMPLE 조회", description = "SAMPLE 조회")
	public ResponseEntity<ApiResponse> findSample() {
		log.info("SampleController - findSample");

		ResultResponse asd = sampleService.findSample();
//		SampleListResponse sampleList = sampleService.findSample();
		log.info("SampleController - findSample - sampleList = {}", asd);

		return ResponseEntity.ok(ApiResponse.success(asd));
	}

	@GetMapping(value = "/sample/{idx}")
	@Operation(summary = "SAMPLE 상세 조회", description = "SAMPLE 상세 조회")
	public ResponseEntity<ApiResponse<SampleViewResponse>> findByIdSample(
			@Parameter(description = "아이디", required = true, in = ParameterIn.PATH, example = "1") @PathVariable(name = "idx") int idx) {
		log.info("SampleController - findByIdSample");
		log.info("SampleController - findByIdSample - idx = {}", idx);

		SampleViewResponse sampleViewResponse = sampleService.findByIdSample(idx);

		return ResponseEntity.ok(ApiResponse.<SampleViewResponse>builder().data(sampleViewResponse).build());
	}

	@PostMapping(value = "/sample")
	@Operation(summary = "SAMPLE 저장", description = "아이디(idx)를 제외한 제목(title)과 내용(contents)을 이용하여 sample을 신규 등록합니다.")
	public ResponseEntity<ApiResponse<Sample>> inputSample(
			@Parameter @RequestBody SampleSaveRequest sampleSaveRequest) {
		log.info("SampleController - inputSample");

		sampleService.inputSample(sampleSaveRequest);

		return ResponseEntity.ok().build();
	}

	@PutMapping(value = "/sample/{idx}")
	@Operation(summary = "SAMPLE 수정", description = "SAMPLE 수정")
	public ResponseEntity<ApiResponse<Sample>> modifySample(
			@Parameter(description = "아이디", required = true, example = "1") @PathVariable(name = "idx") int idx
			, @Parameter @RequestBody SampleSaveRequest sampleSaveRequest) {
		log.info("SampleController - modifySample");
		log.info("SampleController - modifySample - idx = {}", idx);

		sampleService.modifySample(idx, sampleSaveRequest);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/sample/{idx}")
	@Operation(summary = "SAMPLE 삭제", description = "SAMPLE 삭제")
	public ResponseEntity<ApiResponse<Sample>> deleteSample(
			@Parameter(description = "아이디", required = true, example = "1") @PathVariable(name = "idx") int idx) {
		log.info("SampleController - deleteSample");
		log.info("SampleController - modifySample - idx = {}", idx);

		sampleService.deleteSample(idx);

		return ResponseEntity.ok().build();
	}
}
