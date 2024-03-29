package ei.algobaroapi.domain.room_member.service;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.room.domain.Room;
import ei.algobaroapi.domain.room_member.domain.RoomMember;
import ei.algobaroapi.domain.room_member.dto.request.HostAutoChangeRequestDto;
import ei.algobaroapi.domain.room_member.dto.request.HostManualChangeRequestDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomExitResponse;
import ei.algobaroapi.domain.room_member.dto.response.RoomHostAutoChangeResponseDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomHostManualResponseDto;
import ei.algobaroapi.domain.room_member.dto.response.RoomMemberResponseDto;
import java.util.List;

public interface RoomMemberService {

    List<RoomMemberResponseDto> createRoomByRoomId(Room room, Member member);

    void joinRoomByRoomShortUuid(String shortUuid, Member member);

    List<RoomMemberResponseDto> validateEnterRoom(String shortUuid, String password, Member member);

    List<RoomMemberResponseDto> getRoomMembersByRoomId(Long roomId);

    RoomMemberResponseDto chageStatusToReady(String roomShortUuid, Long memberId);

    RoomMemberResponseDto chageStatusToUnready(String roomShortUuid, Long memberId);

    RoomHostManualResponseDto changeHostManually(
            HostManualChangeRequestDto hostManualChangeRequestDto)
            ;

    RoomHostAutoChangeResponseDto changeHostAutomatically(
            HostAutoChangeRequestDto hostAutoChangeRequestDto
    );

    List<RoomMember> getByRoomIdAllReady(Long roomId);

    RoomExitResponse exitRoomByMemberId(Long memberId);

    void validateHost(String roomShortUuid, Long memberId);
}
