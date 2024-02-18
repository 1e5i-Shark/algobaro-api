package ei.algobaroapi.domain.room_member.service;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.room_member.domain.RoomMember;
import ei.algobaroapi.domain.room_member.dto.response.RoomHostResponseDto;

public interface RoomMemberService {

    void joinRoomByRoomId(Long roomId, Member member);

    RoomHostResponseDto changeHostManually(Long hostId, Long organizerId);

    RoomHostResponseDto changeHostAutomatically(RoomMember roomMember);

}
