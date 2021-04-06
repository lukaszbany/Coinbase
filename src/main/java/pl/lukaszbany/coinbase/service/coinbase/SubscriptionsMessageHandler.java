package pl.lukaszbany.coinbase.service.coinbase;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.lukaszbany.coinbase.service.coinbase.dto.Channel;
import pl.lukaszbany.coinbase.service.coinbase.dto.InMessage;
import pl.lukaszbany.coinbase.service.coinbase.factory.MessageTypes;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
@Component
public class SubscriptionsMessageHandler extends InMessageHandler {

    private final MessageTypes messageType;

    public SubscriptionsMessageHandler() {
        this.messageType = MessageTypes.SUBSCRIPTIONS;
    }

    @Override
    public void handle(InMessage inMessage) {
        log.info("Subscribed to channels: {}", toText(inMessage.getChannels()));
    }

    private String toText(List<Channel> channels) {
        return channels
                .stream()
                .map(Channel::getName)
                .collect(Collectors.joining(","));
    }
}
