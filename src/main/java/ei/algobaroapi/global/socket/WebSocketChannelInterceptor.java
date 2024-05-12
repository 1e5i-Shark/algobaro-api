package ei.algobaroapi.global.socket;

import ei.algobaroapi.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChannelInterceptor implements ChannelInterceptor {

    private static final String BEARER_PREFIX = "Bearer ";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final JwtProvider jwtProvider;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        final StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (accessor.getCommand() == StompCommand.CONNECT ||
                accessor.getCommand() == StompCommand.SUBSCRIBE ||
                accessor.getCommand() == StompCommand.SEND) {
            try {
                String token = extractToken(accessor);
                if (!jwtProvider.validateToken(token)) {
                    log.warn("jwtProvider에서 validate 하지 못한 값");
                    throw new IllegalArgumentException("Invalid token");
                }
            } catch (Exception e) {
                log.error("Authentication error: " + e.getMessage(), e);
                return null;
            }
        }
        return message;
    }

    private String extractToken(StompHeaderAccessor accessor) {
//        String authorization = accessor.getFirstNativeHeader(AUTHORIZATION_HEADER);
        String authorization = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0bmd1c0BuYXZlci5jb20iLCJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNzEzNzU4ODUxLCJleHAiOjE3MTYzNTA4NTF9.5B7899g1Q6A99EeZVRRUHWTeZaaQ8L2SSEOqTtomeFI";
        if (authorization == null) {
            log.warn("토큰이 null 입니다.");
            throw new IllegalArgumentException("token is null");
        } else if (!authorization.startsWith(BEARER_PREFIX)) {
            log.warn("Bearer 로 시작하지 않는 토큰입니다.");
            throw new IllegalArgumentException("invalid token");
        }
        return authorization.substring(BEARER_PREFIX.length());
    }
}
