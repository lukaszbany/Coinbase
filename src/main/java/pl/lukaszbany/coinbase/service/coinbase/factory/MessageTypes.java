package pl.lukaszbany.coinbase.service.coinbase.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor
public enum MessageTypes {

    SUBSCRIBE("subscribe"),
    SUBSCRIPTIONS("subscriptions"),
    TICKER("ticker");

    private final String name;

    public static Optional<MessageTypes> byName(String name) {
        for (MessageTypes value : values()) {
            if (value.getName().equals(name)) {
                return Optional.of(value);
            }
        }

        return Optional.empty();
    }
}
