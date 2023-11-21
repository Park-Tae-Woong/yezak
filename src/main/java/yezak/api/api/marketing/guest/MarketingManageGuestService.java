package yezak.api.api.marketing.guest;

import org.springframework.web.multipart.MultipartFile;
import yezak.api.global.common.ResultResponse;

public interface MarketingManageGuestService {
    ResultResponse<?> getList();

    ResultResponse<?> registDb(String title, Long accessRootId, Long amount, String executionDate, MultipartFile file);
}
