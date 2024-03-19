package ei.algobaroapi.domain.room.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>, RoomRepositoryQuery {

    @Query("SELECT r FROM Room r WHERE r.roomUuid LIKE CONCAT(:roomShortUuid, '%')")
    Optional<Room> findByRoomUuidStartingWith(String roomShortUuid);

    @Query(value =
            "SELECT * FROM room WHERE DATE_ADD(start_at, INTERVAL time_limit + :timeOffsetMinutes MINUTE) < :now",
            nativeQuery = true
    )
    List<Room> findListEndAtBefore(LocalDateTime now, int timeOffsetMinutes);
}
