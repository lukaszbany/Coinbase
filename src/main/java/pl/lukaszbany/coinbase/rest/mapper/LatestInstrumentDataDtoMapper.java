package pl.lukaszbany.coinbase.rest.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lukaszbany.coinbase.domain.instrument.Instrument;
import pl.lukaszbany.coinbase.domain.instrument.repository.InstrumentRepository;
import pl.lukaszbany.coinbase.domain.price.data.PriceData;
import pl.lukaszbany.coinbase.domain.price.data.repository.PriceDataRepository;
import pl.lukaszbany.coinbase.rest.dto.LatestInstrumentDataDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LatestInstrumentDataDtoMapper {

    private final InstrumentRepository instrumentRepository;

    private final PriceDataRepository priceDataRepository;

    @Autowired
    public LatestInstrumentDataDtoMapper(InstrumentRepository instrumentRepository, PriceDataRepository priceDataRepository) {
        this.instrumentRepository = instrumentRepository;
        this.priceDataRepository = priceDataRepository;
    }

    public List<LatestInstrumentDataDTO> getLatestInstrumentsDataDTO() {
        return instrumentRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private LatestInstrumentDataDTO toDTO(Instrument instrument) {
        return priceDataRepository.findByInstrument(instrument)
                .map(this::toLatestInstrumentDataDTO)
                .orElseGet(() -> emptyInstrumentDataDTO(instrument));
    }

    private LatestInstrumentDataDTO toLatestInstrumentDataDTO(PriceData priceData) {
        Instrument instrument = priceData.getInstrument();

        return LatestInstrumentDataDTO.builder()
                .instrument(instrument.getName())
                .ask(priceData.getAsk())
                .bid(priceData.getBid())
                .last(priceData.getLast())
                .time(priceData.getTime())
                .build();
    }

    private LatestInstrumentDataDTO emptyInstrumentDataDTO(Instrument instrument) {
        return LatestInstrumentDataDTO.builder()
                .instrument(instrument.getName())
                .build();
    }
}
