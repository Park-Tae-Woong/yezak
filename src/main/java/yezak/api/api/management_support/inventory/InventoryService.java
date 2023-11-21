package yezak.api.api.management_support.inventory;

import yezak.api.api.management_support.inventory.dto.CreateInventoryLogReq;
import yezak.api.api.management_support.inventory.dto.UseMaterialReq;
import yezak.api.global.common.ResultResponse;

import java.util.List;

public interface InventoryService {

    ResultResponse<?> getInventoryList(String searchValue, String sortType, Integer pageNum, Integer pageSize);
    ResultResponse<?> getInventoryDetail(Long id);
    ResultResponse<?> createInventoryLog(CreateInventoryLogReq createInventoryLogReq);
    ResultResponse<?> getMaterialList(String searchValue, Integer pageNum, Integer pageSize);
    ResultResponse<?> useMaterial(List<UseMaterialReq> useMaterialReqs);
    ResultResponse<?> inventoryLogList (String start, String end, Long controlTypeId, String searchValue, int pageNum, int pageSize);





}
