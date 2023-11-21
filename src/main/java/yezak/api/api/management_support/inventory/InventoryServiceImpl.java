package yezak.api.api.management_support.inventory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yezak.api.api.management_support.inventory.dto.*;
import yezak.api.global.common.ResultResponse;
import yezak.api.util.Page;

import java.util.List;
import java.util.Objects;

import static yezak.api.config.MyIdConfig.*;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class InventoryServiceImpl implements InventoryService{
    private final InventoryMapper inventoryMapper;
    private final Integer invenId = 41;
    private final Integer invenLogId = 42;

    @Override
    public ResultResponse<?> getInventoryList(String searchValue, String sortType, Integer pageNum, Integer pageSize) {
        if(myDepth3Id().contains(invenId) || myDepth3Id().contains(invenLogId)) {
            Integer offset = (pageNum - 1) * pageSize;
            if (sortType == null){
                sortType = "desc";
            }
            InventoryListReq inventoryListReq = InventoryListReq.builder()
                    .searchValue(searchValue)
                    .sortType(sortType)
                    .hospitalId(myHospitalId())
                    .offset(offset)
                    .pageSize(pageSize)
                    .build();
            Integer totalCount = inventoryMapper.countInventory(inventoryListReq);
            Page page = Page.builder()
                    .pageNum(pageNum)
                    .pageSize(pageSize)
                    .totalCount(totalCount)
                    .totalPage((int) Math.ceil((double)totalCount / pageSize))
                    .build();

            return ResultResponse.builder()
                    .data(inventoryMapper.getInventoryList(inventoryListReq))
                    .page(page)
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }

    @Override
    public ResultResponse<?> getInventoryDetail(Long id) {
        if(myDepth3Id().contains(invenId) || myDepth3Id().contains(invenLogId)) {
            return ResultResponse.builder()
                    .data(inventoryMapper.getInventoryDetail(myHospitalId(), id))
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }

    @Override
    public ResultResponse<?> createInventoryLog(CreateInventoryLogReq createInventoryLogReq) {
        if(myDepth3Id().contains(invenLogId)) {
            Long invenId = createInventoryLogReq.getHospitalInventoryId();
            if (!Objects.equals(inventoryMapper.findHospitalId(invenId), myHospitalId())) {
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("다른 병원의 재고는 조정 불가능합니다.")
                        .build();
            }

            Integer beforeAmount = inventoryMapper.findAmount(createInventoryLogReq.getHospitalInventoryId());
            int afterAmount;
            UpdateAmountReq updateAmountReq = new UpdateAmountReq(createInventoryLogReq.getControlledAmount(), createInventoryLogReq.getHospitalInventoryId());
            if (createInventoryLogReq.getControlTypeId() == 1) {
                afterAmount = beforeAmount + createInventoryLogReq.getControlledAmount();
                inventoryMapper.plusAmount(updateAmountReq);
            } else {
                afterAmount = beforeAmount - createInventoryLogReq.getControlledAmount();
                inventoryMapper.minusAmount(updateAmountReq);
            }

            CreateInventoryLogReq req = CreateInventoryLogReq.builder()
                    .controlledAt(createInventoryLogReq.getControlledAt())
                    .controlTypeId(createInventoryLogReq.getControlTypeId())
                    .beforeAmount(beforeAmount)
                    .controlledAmount(createInventoryLogReq.getControlledAmount())
                    .afterAmount(afterAmount)
                    .remark(createInventoryLogReq.getRemark())
                    .hospitalInventoryId(createInventoryLogReq.getHospitalInventoryId())
                    .userId(myUserId())
                    .build();

            inventoryMapper.createInventoryLog(req);

            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("등록되었습니다.")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

    @Override
    public ResultResponse<?> getMaterialList(String searchValue, Integer pageNum, Integer pageSize) {
        if(myDepth3Id().contains(invenId) || myDepth3Id().contains(invenLogId)) {
            Integer offset = (pageNum - 1) * pageSize;

            MaterialListReq materialListReq = new MaterialListReq(searchValue, myHospitalId(), offset, pageSize);

            Integer totalCount = inventoryMapper.countMaterial(materialListReq);
            Page page = Page.builder()
                    .pageNum(pageNum)
                    .pageSize(pageSize)
                    .totalCount(totalCount)
                    .totalPage((int) Math.ceil((double)totalCount / pageSize))
                    .build();

            return ResultResponse.builder()
                    .data(inventoryMapper.getMaterialList(materialListReq))
                    .page(page)
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

    @Override
    public ResultResponse<?> useMaterial(List<UseMaterialReq> useMaterialReqs) {
        if(myDepth3Id().contains(invenId) || myDepth3Id().contains(invenLogId)) {
            for(UseMaterialReq useMaterialReq : useMaterialReqs) {
                UseMaterialReq req = UseMaterialReq.builder()
                        .hospitalId(myHospitalId())
                        .materialId(useMaterialReq.getMaterialId())
                        .build();
                inventoryMapper.useMaterial(req);

            }
            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("등록되었습니다.")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }

    @Override
    public ResultResponse<?> inventoryLogList(String start, String end, Long controlTypeId, String searchValue, int pageNum, int pageSize) {
        if(myDepth3Id().contains(invenLogId)) {
            int offset = (pageNum - 1) * pageSize;

            InventoryLogListReq inventoryLogListReq = InventoryLogListReq.builder()
                    .start(start)
                    .end(end)
                    .controlTypeId(controlTypeId)
                    .searchValue(searchValue)
                    .hospitalId(myHospitalId())
                    .offset(offset)
                    .pageSize(pageSize)
                    .build();

            List<InventoryLogListRes> inventoryLogListRes = inventoryMapper.inventoryLogList(inventoryLogListReq);

            Page page = Page.builder()
                    .pageNum(pageNum)
                    .pageSize(pageSize)
                    .totalCount(inventoryLogListRes.size())
                    .totalPage((int) Math.ceil((double)inventoryLogListRes.size() / pageSize))
                    .build();

            return ResultResponse.builder()
                    .data(inventoryLogListRes)
                    .resultCode("200")
                    .result(true)
                    .page(page)
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }

    }
}
