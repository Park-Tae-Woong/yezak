package yezak.api.sample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "SAMPLE 저장 모델")
public class SampleSaveRequest {

	@Schema(description = "SAMPLE 제목")
	private String title;
	@Schema(description = "SAMPLE 내용")
	private String contents;

	@Builder
	public SampleSaveRequest(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}
}