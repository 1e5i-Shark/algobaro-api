package ei.algobaroapi.global.socket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;

@Component
public class WebSocketErrorHandler extends StompSubProtocolErrorHandler {

}
