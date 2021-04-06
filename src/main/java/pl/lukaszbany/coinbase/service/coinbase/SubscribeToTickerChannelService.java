package pl.lukaszbany.coinbase.service.coinbase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import pl.lukaszbany.coinbase.domain.instrument.Instrument;
import pl.lukaszbany.coinbase.domain.instrument.repository.InstrumentRepository;
import pl.lukaszbany.coinbase.service.coinbase.factory.TextMessageFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SubscribeToTickerChannelService {

    private final InstrumentRepository instrumentRepository;

    private final TextMessageFactory textMessageFactory;

    @Autowired
    public SubscribeToTickerChannelService(InstrumentRepository instrumentRepository, TextMessageFactory textMessageFactory) {
        this.instrumentRepository = instrumentRepository;
        this.textMessageFactory = textMessageFactory;
    }

    public void subscribe(WebSocketSession session) {
        List<String> productIds = findAllProductIds();
        subscribeToTickerChannel(session, productIds);
    }

    private List<String> findAllProductIds() {
        return instrumentRepository.findAll()
                .stream()
                .map(Instrument::getExternalId)
                .collect(Collectors.toList());
    }

    private void subscribeToTickerChannel(WebSocketSession session, List<String> productIds) {
        try {
            TextMessage textMessage = textMessageFactory.buildSubscribeTickerChannelMessage(productIds);
            session.sendMessage(textMessage);
        } catch (IOException e) {
            log.info("Cannot subscribe to ticker channel!", e);
        }
    }
}
