package yezak.api.sample.exception;

import yezak.api.global.error.exception.EntityNotFoundException;

public class SampleNotFoundException extends EntityNotFoundException {

	public SampleNotFoundException(Long target) {
		super(target + " is not found");
	}
}