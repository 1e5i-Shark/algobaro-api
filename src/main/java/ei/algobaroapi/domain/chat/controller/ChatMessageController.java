package ei.algobaroapi.domain.chat.controller;

import ei.algobaroapi.domain.chat.dto.MessageRequest;
import ei.algobaroapi.domain.chat.service.ChatService;
import ei.algobaroapi.domain.member.domain.Member;
import ei.algobaroapi.domain.member.service.MemberService;
import ei.algobaroapi.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatService chatService;
    private final JwtProvider jwtProvider;
    private final MemberService memberService;

    @MessageMapping("/chat/enter")
    public void enter(
            MessageRequest messageRequestDto,
            @Header("Authorization") String authorization
    ) {
        chatService.enterRoom(
                messageRequestDto.getRoomShortUuid(),
                tempParseMemberIdFromHeader(authorization)
        );
    }

    @MessageMapping("/chat/quit")
    public void quit(
            MessageRequest messageRequestDto,
            @Header("Authorization") String authorization
    ) {
        chatService.quitRoom(messageRequestDto.getRoomShortUuid(),
                tempParseMemberIdFromHeader(authorization));
    }

    @MessageMapping("/chat/message")
    public void message(
            MessageRequest messageRequestDto,
            @Header("Authorization") String authorization
    ) {
        chatService.convertAndSendMessage(
                messageRequestDto.getRoomShortUuid(),
                tempParseMemberIdFromHeader(authorization),
                messageRequestDto.getMessage()
        );
    }

    @MessageMapping("/chat/ready")
    public void ready(
            MessageRequest messageRequestDto,
            @Header("Authorization") String authorization
    ) {
        chatService.readyRoom(
                messageRequestDto.getRoomShortUuid(),
                tempParseMemberIdFromHeader(authorization)
        );
    }

    @MessageMapping("/chat/unready")
    public void unready(
            MessageRequest messageRequestDto,
            @Header("Authorization") String authorization
    ) {
        chatService.unreadyRoom(
                messageRequestDto.getRoomShortUuid(),
                tempParseMemberIdFromHeader(authorization)
        );
    }

    @MessageMapping("/chat/change-host")
    public void changeHostManually(
            MessageRequest messageRequestDto,
            @Header("Authorization") String authorization
    ) {
        chatService.changeHostManually(
                messageRequestDto.getRoomShortUuid(),
                tempParseMemberIdFromHeader(authorization),
                Long.parseLong(messageRequestDto.getMessage())
        );
    }

    @MessageExceptionHandler
    public String exception() {
        return "Error has occurred.";
    }

    // 임시 사용, 추후 Custom Annotation으로 변경 예정
    private Long tempParseMemberIdFromHeader(String authorization) {
        String emailByToken = jwtProvider.getUserEmailByToken(authorization.substring(7));
        Member memberByEmail = memberService.getMemberByEmail(emailByToken);
        return memberByEmail.getId();
    }
}
