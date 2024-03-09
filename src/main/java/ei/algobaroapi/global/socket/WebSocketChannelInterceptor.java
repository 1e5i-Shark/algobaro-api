package ei.algobaroapi.global.socket;

import ei.algobaroapi.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

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
            String token = extractToken(accessor);
            if (!jwtProvider.validateToken(token)) {
                throw new IllegalArgumentException("Invalid token");
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
