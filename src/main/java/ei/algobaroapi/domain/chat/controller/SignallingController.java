package ei.algobaroapi.domain.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SignallingController {

    @MessageMapping("/peer/offer/{camKey}/{roomShortUuid}")
    @SendTo("/topic/peer/offer/{camKey}/{roomShortUuid}")
    public String peerHandleOffer(
            @Payload String offer,
            @Header("Authorization") String authorization,
            @DestinationVariable(value = "roomShortUuid") String roomShortUuid,
            @DestinationVariable(value = "camKey") String camKey
    ) {
        log.info("[OFFER] {} : {}", camKey, offer);
        return offer;
    }

    //iceCandidate 정보를 주고 받기 위한 webSocket
    //camKey : 각 요청하는 캠의 key , roomShortUuid : 룸 아이디
    @MessageMapping("/peer/iceCandidate/{camKey}/{roomShortUuid}")
    @SendTo("/topic/peer/iceCandidate/{camKey}/{roomShortUuid}")
    public String peerHandleIceCandidate(
            @Payload String candidate,
            @Header("Authorization") String authorization,
            @DestinationVariable(value = "roomShortUuid") String roomShortUuid,
            @DestinationVariable(value = "camKey") String camKey
    ) {
        log.info("[ICECANDIDATE] {} : {}", camKey, candidate);
        return candidate;
    }

    //
    @MessageMapping("/peer/answer/{camKey}/{roomShortUuid}")
    @SendTo("/topic/peer/answer/{camKey}/{roomShortUuid}")
    public String peerHandleAnswer(
            @Payload String answer,
            @Header("Authorization") String authorization,
            @DestinationVariable(value = "roomShortUuid") String roomShortUuid,
            @DestinationVariable(value = "camKey") String camKey
    ) {
        log.info("[ANSWER] {} : {}", camKey, answer);
        return answer;
    }

    //camKey 를 받기위해 신호를 보내는 webSocket
    @MessageMapping("/call/key")
    @SendTo("/topic/call/key")
    public String callKey(
            @Payload String message,
            @Header("Authorization") String authorization
    ) {
        log.info("[Key] : {}", message);
        return message;
    }

    //자신의 camKey 를 모든 연결된 세션에 보내는 webSocket
    @MessageMapping("/send/key")
    @SendTo("/topic/send/key")
    public String sendKey(
            @Payload String message,
            @Header("Authorization") String authorization
    ) {
        return message;
    }
}
