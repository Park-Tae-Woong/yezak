package yezak.api.sample.service;

import yezak.api.global.common.ResultResponse;
import yezak.api.sample.dto.SampleListResponse;
import yezak.api.sample.dto.SampleSaveRequest;
import yezak.api.sample.dto.SampleViewResponse;


public interface SampleService {
	public ResultResponse findSample();

	public SampleViewResponse findByIdSample(int idx);

	public void inputSample(SampleSaveRequest sampleSaveRequest);

	public void modifySample(int idx, SampleSaveRequest sampleSaveRequest);

	public void deleteSample(int idx);
}
