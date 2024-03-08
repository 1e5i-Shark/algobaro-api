package ei.algobaroapi.domain.room_member.domain;

import ei.algobaroapi.domain.room.domain.Room;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomMemberRepository extends JpaRepository<RoomMember, Long> {

    List<RoomMember> findByRoomId(Long roomId);

    @Query("select rm from RoomMember rm where rm.room.id = :roomId and rm.member.id = :id")
    Optional<RoomMember> findRoomMemberByRoomIdAndMemberId(Long roomId, Long id);

    @Query("SELECT r FROM RoomMember rm JOIN rm.room r WHERE rm.member.id = :memberId")
    Optional<Room> findRoomByMemberId(Long memberId);
}
