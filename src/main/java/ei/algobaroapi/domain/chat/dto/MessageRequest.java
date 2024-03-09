package ei.algobaroapi.domain.chat.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MessageRequest {

    private String roomShortUuid;

    private String message;
}
