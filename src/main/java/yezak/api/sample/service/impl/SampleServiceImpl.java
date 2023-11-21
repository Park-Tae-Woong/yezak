package yezak.api.sample.service.impl;

import yezak.api.global.common.ResultResponse;
import yezak.api.sample.domain.Sample;
import yezak.api.sample.dto.SampleListResponse;
import yezak.api.sample.dto.SampleSaveRequest;
import yezak.api.sample.dto.SampleViewResponse;
import yezak.api.sample.mapper.SampleMapper;
import yezak.api.sample.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class SampleServiceImpl implements SampleService {

	private final SampleMapper sampleMapper;

	@Override
	public ResultResponse findSample() {
		ResultResponse asd = new ResultResponse();
		asd.setResult(false);

		try {
			int totalCount = sampleMapper.findSampleTotalCount();
			List<Sample> sampleList = (List<Sample>) sampleMapper.findSample();

			List<SampleViewResponse> listRes = new ArrayList<>();
			SampleViewResponse sampleViewResponse = null;
			SampleListResponse sampleListResponse = null;

			for(Sample sample : sampleList) {
				sampleViewResponse = SampleViewResponse.builder()
						.idx(sample.getIdx())
						.title(sample.getTitle())
						.contents(sample.getContents())
						.build();

				listRes.add(sampleViewResponse);
			}

			sampleListResponse = SampleListResponse.builder()
					.totalCount(totalCount)
					.sampleViewResponseList(listRes)
					.build();

			asd.setData(sampleListResponse);

			asd.setResultCode("성골");
			asd.setResult(true);
		}catch (Exception e) {
			asd.setResultCode("땡");
			asd.setResultMessage(e.getMessage());
		}

		return asd;
	}

	@Override
	public SampleViewResponse findByIdSample(int idx) {
		SampleViewResponse sampleViewResponse = null;
		Sample sample = sampleMapper.findByIdSample(idx);

		sampleViewResponse = SampleViewResponse.builder()
				.idx(sample.getIdx())
				.title(sample.getTitle())
				.contents(sample.getContents())
				.build();

		return sampleViewResponse;
	}

	@Override
	@Transactional
	public void inputSample(SampleSaveRequest sampleSaveRequest) {
		Sample sample = Sample.builder()
				.title(sampleSaveRequest.getTitle())
				.contents(sampleSaveRequest.getContents())
				.build();

		sampleMapper.inputSample(sample);
	}

	@Override
	@Transactional
	public void modifySample(int idx, SampleSaveRequest sampleSaveRequest) {
		Sample sample = Sample.builder()
				.idx(idx)
				.title(sampleSaveRequest.getTitle())
				.contents(sampleSaveRequest.getContents())
				.build();

		sampleMapper.modifySample(sample);
	}

	@Override
	@Transactional
	public void deleteSample(int idx) {
		sampleMapper.deleteSample(idx);
	}
}