package pl.lukaszbany.coinbase.service.coinbase.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import pl.lukaszbany.coinbase.service.coinbase.dto.InMessage;

import java.util.List;

@Service
public class InMessageFactory {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

    public InMessage buildInMessages(TextMessage textMessage) throws JsonProcessingException {
        String payload = textMessage.getPayload();
        return OBJECT_MAPPER.readValue(payload, InMessage.class);
    }
}
