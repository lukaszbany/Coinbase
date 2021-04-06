package pl.lukaszbany.coinbase.domain.instrument.repository;

import com.google.common.collect.ImmutableSet;
import org.springframework.stereotype.Component;
import pl.lukaszbany.coinbase.domain.instrument.Instrument;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class InMemoryInstrumentRepository implements InstrumentRepository {

    private static final Set<Instrument> INSTRUMENTS = ImmutableSet.<Instrument>builder()
            .add(new Instrument("BTCUSD", "BTC-USD"))
            .add(new Instrument("BTCEUR", "BTC-EUR"))
            .add(new Instrument("ETHUSD", "ETH-USD"))
            .add(new Instrument("ETHEUR", "ETH-EUR"))
            .build();

    private static final Map<String, Instrument> INSTRUMENTS_BY_EXTERNAL_ID =
            INSTRUMENTS
                    .stream()
                    .collect(Collectors.toMap(Instrument::getExternalId, instrument -> instrument));

    @Override
    public Set<Instrument> findAll() {
        return new HashSet<>(INSTRUMENTS);
    }

    @Override
    public Optional<Instrument> findByExternalId(String externalId) {
        return Optional.ofNullable(
                INSTRUMENTS_BY_EXTERNAL_ID.get(externalId)
        );
    }


}
