package pl.lukaszbany.coinbase.service.coinbase;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lukaszbany.coinbase.domain.instrument.Instrument;
import pl.lukaszbany.coinbase.domain.instrument.repository.InstrumentRepository;
import pl.lukaszbany.coinbase.domain.price.data.PriceData;
import pl.lukaszbany.coinbase.domain.price.data.repository.PriceDataRepository;
import pl.lukaszbany.coinbase.service.coinbase.dto.InMessage;
import pl.lukaszbany.coinbase.service.coinbase.factory.MessageTypes;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
@Getter
@Component
public class TickerInMessageHandler extends InMessageHandler {

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'";

    private final InstrumentRepository instrumentRepository;

    private final PriceDataRepository priceDataRepository;

    private final MessageTypes messageType;

    public TickerInMessageHandler(InstrumentRepository instrumentRepository, PriceDataRepository priceDataRepository) {
        this.messageType = MessageTypes.TICKER;
        this.instrumentRepository = instrumentRepository;
        this.priceDataRepository = priceDataRepository;
    }

    @Override
    void handle(InMessage inMessage) {
        String productId = inMessage.getProductId();
        instrumentRepository.findByExternalId(productId)
                .ifPresentOrElse(
                        instrument -> savePriceData(instrument, inMessage),
                        logNoInstrumentFound(inMessage)
                );
    }

    private void savePriceData(Instrument instrument, InMessage inMessage) {
        PriceData priceData = buildPriceData(instrument, inMessage);
        priceDataRepository.save(priceData);
    }

    private PriceData buildPriceData(Instrument instrument, InMessage inMessage) {

        LocalDateTime localDateTime = LocalDateTime.parse(inMessage.getTime(), DateTimeFormatter.ofPattern(FORMAT));


        return PriceData.builder()
                .instrument(instrument)
                .bid(new BigDecimal(inMessage.getBestBid()))
                .ask(new BigDecimal(inMessage.getBestAsk()))
                .last(new BigDecimal(inMessage.getPrice()))
                .time(Time.valueOf(localDateTime.toLocalTime()))
                .build();
    }

    private Runnable logNoInstrumentFound(InMessage inMessage) {
        return () -> log.info("No instrument with external id {} found", inMessage.getProductId());
    }

}
