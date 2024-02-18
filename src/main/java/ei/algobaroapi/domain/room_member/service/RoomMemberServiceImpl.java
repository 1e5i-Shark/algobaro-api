package ei.algobaroapi.domain.room_member.service;

import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.room_member.domain.RoomMember;
import ei.algobaroapi.domain.room_member.dto.response.RoomHostResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RoomMemberServiceImpl implements RoomMemberService {

    @Override
    public void joinRoomByRoomId(Long roomId, Member member) {

    }

    @Override
    public RoomHostResponseDto changeHostManually(Long hostId, Long organizerId) {
        return null;
    }

    @Override
    public RoomHostResponseDto changeHostAutomatically(RoomMember roomMember) {
        return null;
    }
}
