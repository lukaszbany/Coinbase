package pl.lukaszbany.coinbase.service.coinbase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class CoinbaseClient {

    private CoinbaseSessionHandler coinbaseSessionHandler;

    @Value("${coinbase.ws-feed.url}")
    private String coinbaseWsFeedUrl;

    @Autowired
    public CoinbaseClient(CoinbaseSessionHandler coinbaseSessionHandler) {
        this.coinbaseSessionHandler = coinbaseSessionHandler;
    }

    @PostConstruct
    private void init() {
        log.info("Initializing CoinbaseClient");
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketConnectionManager connectionManager = new WebSocketConnectionManager(client, coinbaseSessionHandler, coinbaseWsFeedUrl);
        connectionManager.start();
    }


}
