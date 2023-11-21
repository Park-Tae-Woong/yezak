package yezak.api.api.management_support.inventory;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yezak.api.api.management_support.inventory.dto.*;

import java.util.List;

@Mapper
public interface InventoryMapper {
    List<InventoryListRes> getInventoryList(InventoryListReq inventoryListReq);
    Integer countInventory(InventoryListReq inventoryListReq);
    InventoryDetailRes getInventoryDetail(@Param("hospitalId")Long hospitalId, @Param("id") Long id);
    void createInventoryLog(CreateInventoryLogReq createInventoryLogReq);
    void plusAmount(UpdateAmountReq updateAmountReq);
    void minusAmount(UpdateAmountReq updateAmountReq);
    Integer findAmount(Long id);
    List<MaterialListRes> getMaterialList(MaterialListReq materialListReq);
    Integer countMaterial(MaterialListReq materialListReq);
    void useMaterial(UseMaterialReq useMaterialReq);
    List<InventoryLogListRes> inventoryLogList (InventoryLogListReq inventoryLogListReq);
    Long findHospitalId(Long id);
}
