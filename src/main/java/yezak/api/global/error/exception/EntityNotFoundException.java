package yezak.api.global.error.exception;

import yezak.api.global.error.ErrorCode;

public class EntityNotFoundException extends BusinessException {

	public EntityNotFoundException(String value) {
		super(value, ErrorCode.ENTITY_NOT_FOUND);
	}
}