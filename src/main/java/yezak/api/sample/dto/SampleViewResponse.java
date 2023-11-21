package yezak.api.sample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "SAMPLE 상세 모델")
public class SampleViewResponse {

	@Schema(description = "SAMPLE 번호")
	private int idx;
	@Schema(description = "SAMPLE 제목")
	private String title;
	@Schema(description = "SAMPLE 내용")
	private String contents;

	@Builder
	public SampleViewResponse(int idx, String title, String contents) {
		this.idx = idx;
		this.title = title;
		this.contents = contents;
	}
}