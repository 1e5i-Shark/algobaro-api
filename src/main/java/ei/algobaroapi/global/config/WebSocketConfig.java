package ei.algobaroapi.global.config;

import ei.algobaroapi.global.socket.WebSocketChannelInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final WebSocketChannelInterceptor webSocketChannelInterceptor;
    private final StompSubProtocolErrorHandler webSocketErrorHandler;
    @Value("${spring.myapp.websocket.endpoint}")
    private String webSocketEndpoint;
    @Value("${spring.myapp.websocket.pub-prefix}")
    private String webSocketPubPrefix;
    @Value("${spring.myapp.websocket.sub-prefix}")
    private String webSocketSubPrefix;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .setErrorHandler(webSocketErrorHandler)
                .addEndpoint(webSocketEndpoint)
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes(webSocketPubPrefix);
        registry.enableSimpleBroker(webSocketSubPrefix);
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(webSocketChannelInterceptor);
    }
}
