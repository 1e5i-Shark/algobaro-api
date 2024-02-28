package ei.algobaroapi.domain.room.domain;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r from Room r where r.roomUuid = :roomUuid")
    Optional<Room> findByRoomUuidWithRoomMember(UUID roomUuid); // TODO: 방 참여 기능 구현 후 쿼리 수정
}
