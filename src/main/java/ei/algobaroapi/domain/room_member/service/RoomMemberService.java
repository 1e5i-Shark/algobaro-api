package ei.algobaroapi.domain.room_member.service;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.room.domain.Room;
import ei.algobaroapi.domain.room_member.domain.RoomMember;
import ei.algobaroapi.domain.room_member.dto.request.HostChangeRequestDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomHostResponseDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomMemberResponseDto;
import java.util.List;

public interface RoomMemberService {

    List<RoomMemberResponseDto> createRoomByRoomId(Room room, Member member);

    List<RoomMemberResponseDto> joinRoomByRoomId(Long roomId, Member member);

    List<RoomMemberResponseDto> getRoomMembersByRoomId(Long roomId);

    RoomHostResponseDto changeHostManually(HostChangeRequestDto hostChangeRequestDto);

    RoomHostResponseDto changeHostAutomatically(RoomMember roomMember);

}
