package yezak.api.api.admin.system;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yezak.api.api.admin.system.dto.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface SystemMapper {

    void saveFileInfo(BackgroundAttachmentDto backgroundAttachmentDto);

    void insertCustom(InsertCustomDepthOne insertCustomDepthOne);
    void customDepth1(SystemDto systemDto);
    Long findByDepthOne(@Param("hospitalId") Long hospitalId, @Param("id") Long id);

    Long findByDepth1 (@Param("depth1NavigationId") Long depth1NavigationId, @Param("hospitalId") Long hospitalId);

    List<SystemMenuInfoResponse> getNaviList();
    String getNaviOneDepthId(@Param("hospitalId") Long hospitalId, @Param("depth1NavigationId") Long depth1NavigationId);

    String checkOriginName(Long id);
    void deleteDepth1Pivot(Long id);
    Integer checkBackgroundState(Long hospitalId);

    Map<String, Object> getBackgroundInfo(Long hospitalId);

    void modifyBackgroundState(SystemManageBackgroundStateChangeRequest request);
}
