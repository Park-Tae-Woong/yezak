package yezak.api.sample.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Schema(description = "SAMPLE 모델")
public class Sample {

	@Schema(description = "SAMPLE 아이디")
	private int idx;
	@Schema(description = "SAMPLE 제목")
	private String title;
	@Schema(description = "SAMPLE 내용")
	private String contents;
}