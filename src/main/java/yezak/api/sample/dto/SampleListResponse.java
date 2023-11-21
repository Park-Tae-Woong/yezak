package yezak.api.sample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Schema(description = "SAMPLE 목록 모델")
public class SampleListResponse {

	@Schema(description = "SAMPLE 총개수")
	private int totalCount;
	@Schema(description = "SAMPLE 목록")
	private List<SampleViewResponse> sampleViewResponseList;

	@Builder
	public SampleListResponse(int totalCount, List<SampleViewResponse> sampleViewResponseList) {
		this.totalCount = totalCount;
		this.sampleViewResponseList = sampleViewResponseList;
	}
}