package pl.lukaszbany.coinbase.service.coinbase.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import pl.lukaszbany.coinbase.service.coinbase.dto.Channel;
import pl.lukaszbany.coinbase.service.coinbase.dto.OutMessage;

import java.util.List;

@Slf4j
@Service
public class TextMessageFactory {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public TextMessage buildSubscribeTickerChannelMessage(List<String> productIds) throws JsonProcessingException {
        OutMessage outMessage = buildOutMessage(productIds);
        log.info("Message to send: {}", outMessage);

        String outMessageJson = OBJECT_MAPPER.writeValueAsString(outMessage);
        return new TextMessage(outMessageJson);
    }

    private OutMessage buildOutMessage(List<String> productIds) {
        List<Channel> channels = buildChannels(productIds);

        return OutMessage.builder()
                .type(MessageTypes.SUBSCRIBE.getName())
                .productIds(productIds)
                .channels(channels)
                .build();
    }

    private List<Channel> buildChannels(List<String> productIds) {
        Channel tickerChannel = Channel.builder()
                .name(Channels.TICKER.getName())
                .productIds(productIds)
                .build();

        return ImmutableList.of(tickerChannel);
    }
}
