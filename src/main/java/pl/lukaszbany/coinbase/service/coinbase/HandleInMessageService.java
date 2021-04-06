package pl.lukaszbany.coinbase.service.coinbase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lukaszbany.coinbase.service.coinbase.dto.InMessage;
import pl.lukaszbany.coinbase.service.coinbase.factory.MessageTypes;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class HandleInMessageService {

    private List<InMessageHandler> inMessageHandlers;

    @Autowired
    public HandleInMessageService(List<InMessageHandler> inMessageHandlers) {
        this.inMessageHandlers = inMessageHandlers;
    }

    public void handleMessage(InMessage inMessage) {
        MessageTypes.byName(inMessage.getType())
                .flatMap(this::toHandler)
                .ifPresentOrElse(
                        handler -> handleMessage(handler, inMessage),
                        logMessageNotSupported(inMessage)
                );

    }

    private Optional<InMessageHandler> toHandler(MessageTypes type) {
        return inMessageHandlers.stream()
                .filter(handler -> handler.isForType(type))
                .findFirst();
    }

    private void handleMessage(InMessageHandler handler, InMessage inMessage) {
        handler.handle(inMessage);
    }

    private Runnable logMessageNotSupported(InMessage inMessage) {
        return () -> log.info("No handler for message: {}. Message type {} not supported", inMessage, inMessage.getType());
    }


}
