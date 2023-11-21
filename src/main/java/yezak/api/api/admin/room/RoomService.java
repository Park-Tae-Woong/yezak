package yezak.api.api.admin.room;

import yezak.api.api.admin.room.dto.RoomMulti;
import yezak.api.global.common.ResultResponse;

public interface RoomService {
    ResultResponse<?> StorageRoom();

    ResultResponse<?> roomList();

    ResultResponse<?> multiProcess(RoomMulti roomMulti);

    ResultResponse<?> getScheduleCategoriesList();
}
