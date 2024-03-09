package ei.algobaroapi.domain.room_member.service;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.room.domain.Room;
import ei.algobaroapi.domain.room_member.domain.RoomMember;
import ei.algobaroapi.domain.room_member.dto.request.HostAutoChangeRequestDto;
import ei.algobaroapi.domain.room_member.dto.request.HostChangeRequestDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomHostDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomHostResponseDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomMemberResponseDto;
import java.util.List;

public interface RoomMemberService {

    List<RoomMemberResponseDto> createRoomByRoomId(Room room, Member member);

    List<RoomMemberResponseDto> joinRoomByRoomId(Long roomId, String password, Member member);

    List<RoomMemberResponseDto> getRoomMembersByRoomId(Long roomId);

    RoomMemberResponseDto changeReadyStatus(Long roomId, Long memberId);

    RoomHostResponseDto changeHostManually(HostChangeRequestDto hostChangeRequestDto);

    RoomHostDto changeHostAutomatically(HostAutoChangeRequestDto hostAutoChangeRequestDto);

    List<RoomMember> getByRoomIdAllReady(Long roomId);

    List<RoomMemberResponseDto> exitRoomByMemberId(Long memberId);
}
