package pl.lukaszbany.coinbase.service.coinbase;

import pl.lukaszbany.coinbase.service.coinbase.dto.InMessage;
import pl.lukaszbany.coinbase.service.coinbase.factory.MessageTypes;

import java.util.Optional;

public abstract class InMessageHandler {

    abstract void handle(InMessage inMessage);

    abstract MessageTypes getMessageType();

    boolean isForType(MessageTypes type) {
        return getMessageType().equals(type);
    }

}
