package yezak.api.sample.mapper;

import yezak.api.sample.domain.Sample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SampleMapper {

	public int findSampleTotalCount();
	public List<Sample> findSample();

	public Sample findByIdSample(int idx);

	public void inputSample(Sample sample);

	public void modifySample(Sample sample);

	public void deleteSample(int idx);

}