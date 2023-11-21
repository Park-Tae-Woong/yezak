package yezak.api.api.setting.imageForm;

import org.apache.ibatis.annotations.Mapper;
import yezak.api.api.setting.imageForm.dto.ImageFormAttachmentDto;
import yezak.api.api.setting.imageForm.dto.ImageFormFilter;
import yezak.api.api.setting.imageForm.dto.ImageFormReq;
import yezak.api.api.setting.imageForm.dto.ImageFormRes;

import java.util.List;

@Mapper
public interface ImageFormMapper {
    void insertForm(ImageFormReq imageFormReq);

    void saveFileInfo(ImageFormAttachmentDto imageFormAttachmentDto);

    List<ImageFormRes> getFormList(ImageFormFilter imageFormFilter);

    void deleteForm(Long id);

    String findDuplicateName(String name);


}
