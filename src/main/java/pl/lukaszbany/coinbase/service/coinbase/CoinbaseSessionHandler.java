package pl.lukaszbany.coinbase.service.coinbase;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import pl.lukaszbany.coinbase.service.coinbase.dto.InMessage;
import pl.lukaszbany.coinbase.service.coinbase.factory.InMessageFactory;

@Slf4j
@Service
public class CoinbaseSessionHandler extends TextWebSocketHandler {

    private final SubscribeToTickerChannelService subscribeToTickerChannelService;

    private final InMessageFactory inMessageFactory;

    private final HandleInMessageService handleInMessageService;

    @Autowired
    public CoinbaseSessionHandler(SubscribeToTickerChannelService subscribeToTickerChannelService, InMessageFactory inMessageFactory, HandleInMessageService handleInMessageService) {
        this.subscribeToTickerChannelService = subscribeToTickerChannelService;
        this.inMessageFactory = inMessageFactory;
        this.handleInMessageService = handleInMessageService;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        log.debug("Message Received [" + message.getPayload() + "]");

        tryToHandle(message);
    }

    private void tryToHandle(TextMessage message) {
        try {
            handle(message);
        } catch (JsonProcessingException e) {
            log.info("Cannot process in message: {}", message.getPayload());
        }
    }

    private void handle(TextMessage message) throws JsonProcessingException {
        InMessage inMessage = inMessageFactory.buildInMessages(message);
        handleInMessageService.handleMessage(inMessage);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Connected to Coinbase ws feed");
        subscribeToTickerChannelService.subscribe(session);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        log.info("Transport Error", exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("Connection Closed [" + status.getReason() + "]");
        //TODO: Handle reconnection to Coinbase in case of connection lost
    }

}
