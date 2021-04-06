package pl.lukaszbany.coinbase.domain.price.data.repository;

import org.springframework.stereotype.Component;
import pl.lukaszbany.coinbase.domain.instrument.Instrument;
import pl.lukaszbany.coinbase.domain.price.data.PriceData;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryPriceDataRepository implements PriceDataRepository {

    private final Map<Instrument, PriceData> latestPriceData;

    public InMemoryPriceDataRepository() {
        this.latestPriceData = new HashMap<>();
    }

    @Override
    public Optional<PriceData> findByInstrument(Instrument instrument) {
        PriceData priceData = latestPriceData.get(instrument);

        return Optional.ofNullable(priceData);
    }

    @Override
    public void save(PriceData priceData) {
        this.latestPriceData.put(priceData.getInstrument(), priceData);
    }
}
