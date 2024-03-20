package ei.algobaroapi.domain.room.service;

import ei.algobaroapi.domain.room.domain.Room;
import ei.algobaroapi.domain.room.domain.RoomRepository;
import ei.algobaroapi.domain.room_member.domain.RoomMemberRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RoomSchedulerServiceImpl implements RoomSchedulerService {

    private static final int DELETE_ROOM_TIME_OFFSET_MINUTES = 30;
    private final RoomRepository roomRepository;
    private final RoomMemberRepository roomMemberRepository;

    @Override
    @Scheduled(cron = "0/10 * * * * *")
    @Transactional
    public void deleteRoomScheduler() {
        List<Room> toDeleteRooms = findExpiredRooms();
        deleteRoomsAndMembers(toDeleteRooms);
    }

    private List<Room> findExpiredRooms() {
        return roomRepository.findListEndAtBefore(
                LocalDateTime.now(),
                DELETE_ROOM_TIME_OFFSET_MINUTES
        );
    }

    private void deleteRoomsAndMembers(List<Room> toDeleteRooms) {
        for (Room toDeleteRoom : toDeleteRooms) {
            roomMemberRepository.deleteAll(toDeleteRoom.getRoomMembers());
        }
        roomRepository.deleteAll(toDeleteRooms);
    }
}
