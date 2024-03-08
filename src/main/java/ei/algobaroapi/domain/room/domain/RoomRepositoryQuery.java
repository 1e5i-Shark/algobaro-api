package ei.algobaroapi.domain.room.domain;

import ei.algobaroapi.domain.room.dto.request.RoomListRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomRepositoryQuery {

    Page<Room> findListPage(RoomListRequestDto request, Pageable pageable);
}
