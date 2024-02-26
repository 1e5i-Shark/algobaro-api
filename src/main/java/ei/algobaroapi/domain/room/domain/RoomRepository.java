package ei.algobaroapi.domain.room.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r from Room r where r.roomUUID = :roomUuid")
    Optional<Room> findByRoomUuidWithRoomMember(String roomUuid); // TODO: 방 참여 기능 구현 후 쿼리 수정
}
