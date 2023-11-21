package yezak.api.api.admin.room;

import org.apache.ibatis.annotations.Mapper;
import yezak.api.api.admin.room.dto.*;

import java.util.List;

@Mapper

public interface RoomMapper {

    RoomDto StorageRoom (Long hospitalId);
    List<RoomDto> roomList(Long hospitalId);

    void deleteRooms(DeleteRoomReq deleteRoomReq);

    void createRooms(CreateRoomReq createRoomReq);

    void updateRooms(UpdateRoomReq updateRoomReq);


    List<ScheduleCategoriesInfo> getScheduleCategoriesList();
}
