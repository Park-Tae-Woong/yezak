package yezak.api.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode implements EnumModel {

	BAD_REQUEST(400, "400", "Bad Request")
	, NOT_FOUND(404, "","Not Found")
	, METHOD_NOT_ALLOWED(405, "", "Method Not Allowed")
	, INTERNAL_SERVER_ERROR(500, "", "Internal Server Error")

	, ENTITY_NOT_FOUND(400, "", "Entity Not Found")
	, INVALID_INPUT_VALUE(400, "400", "Invalid Input Value")
	, INVALID_TYPE_VALUE(400, "", "Invalid Type Value")
	, HANDLE_ACCESS_DENIED(403, "", "Handle Access Denied")
	, FILE_SIZE_EXCEED(403, "", "File Size Exceed")
	, DUPLICATE(200, "10001", "Data Is Duplicated")
	, NO_DATA(200, "10002", "There Is No Data")
	, WRONG_DATA(200, "10003",  "Data is Wrong")
	, NO_LOGIN(200, "20001", "There Is No Login Info")


	// 230725~
	, DATA_ERROR(300, "300", "Data Error")
	, DATA_IS_NULL(310, "310", "Data is Null")
	;

	private int status;
	private String code;
	private String message;

	ErrorCode(final int status, final String code, final String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

	@Override
	public String getKey() {
		return this.code;
	}

	@Override
	public String getValue() {
		return this.message;
	}
}