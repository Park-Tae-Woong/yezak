package yezak.api.api.admin.room;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yezak.api.api.admin.room.dto.CreateRoomReq;
import yezak.api.api.admin.room.dto.DeleteRoomReq;
import yezak.api.api.admin.room.dto.RoomMulti;
import yezak.api.api.admin.room.dto.UpdateRoomReq;
import yezak.api.global.common.ResultResponse;

import static yezak.api.config.MyIdConfig.myDepth3Id;
import static yezak.api.config.MyIdConfig.myHospitalId;

@RequiredArgsConstructor
@Service
@Slf4j
public class RoomServiceImpl implements RoomService{

    private final RoomMapper roomMapper;
    private final Integer roomId = 54;
    @Override
    public ResultResponse<?> StorageRoom() {
        if(myDepth3Id().contains(roomId)) {
            return ResultResponse.builder()
                    .data(roomMapper.StorageRoom(myHospitalId()))
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
    public ResultResponse<?> roomList() {
        if(myDepth3Id().contains(roomId)) {
            return ResultResponse.builder()
                    .data(roomMapper.roomList(myHospitalId()))
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
    @Transactional
    public ResultResponse<?> multiProcess(RoomMulti roomMulti) {
        if(myDepth3Id().contains(roomId)) {
            if (roomMulti.getCreateRoomReqs() != null) {
                for (CreateRoomReq createRoomReq : roomMulti.getCreateRoomReqs()){
                    createRoomReq.setHospitalId(myHospitalId());
                    roomMapper.createRooms(createRoomReq);
                }
            }
            if (roomMulti.getUpdateRoomReqs() != null) {
                for (UpdateRoomReq updateRoomReq : roomMulti.getUpdateRoomReqs()) {
                    updateRoomReq.setHospitalId(myHospitalId());
                    roomMapper.updateRooms(updateRoomReq);
                }
            }
            if (roomMulti.getDeleteRooms() != null) {
                for (DeleteRoomReq deleteRoomReq : roomMulti.getDeleteRooms()) {
                    roomMapper.deleteRooms(deleteRoomReq);
                }
            }
            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("수정되었습니다.")
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
    public ResultResponse<?> getScheduleCategoriesList() {
        if(myDepth3Id().contains(roomId)) {
            return ResultResponse.builder()
                    .data(roomMapper.getScheduleCategoriesList())
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

}
