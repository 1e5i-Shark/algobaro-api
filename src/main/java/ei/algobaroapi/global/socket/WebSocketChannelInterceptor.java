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
                if (token == null || !jwtProvider.validateToken(token)) {
                    log.warn("토큰 null or 유효하지 않은 토큰");
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
        String authorization = accessor.getFirstNativeHeader(AUTHORIZATION_HEADER);
        if (authorization == null || !authorization.startsWith(BEARER_PREFIX)) {
            throw new IllegalArgumentException("Invalid token");
        }
        return authorization.substring(BEARER_PREFIX.length());
    }
}
