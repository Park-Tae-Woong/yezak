package yezak.api.api.admin.room.dto;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class RoomMulti {
    private List<CreateRoomReq> createRoomReqs;
    private List<UpdateRoomReq> updateRoomReqs;
    private List<DeleteRoomReq> deleteRooms;


}
