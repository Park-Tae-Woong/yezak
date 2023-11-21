package yezak.api.global.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {


	private T data;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T select;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T page;
	private Boolean result;
	private String resultCode;
	private String resultMessage;

	public ApiResponse(T data, T select, T page, Boolean result, String resultCode, String resultMessage) {
		this.data = data;
		this.select = select;
		this.page = page;
		this.result = result;
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
	}

	public static <T> ApiResponse<T> success(ResultResponse resultResponse) {
		return new ApiResponse<T>((T) resultResponse.getData(), (T) resultResponse.getSelect(), (T) resultResponse.getPage(), resultResponse.getResult(), resultResponse.getResultCode(), resultResponse.getResultMessage());
	}

	public static <T> ApiResponse<T> success() {
		return new ApiResponse<T>(null, null, null, null, null, null);
	}

}