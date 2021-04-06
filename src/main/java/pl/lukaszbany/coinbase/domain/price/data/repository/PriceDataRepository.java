package pl.lukaszbany.coinbase.domain.price.data.repository;

import pl.lukaszbany.coinbase.domain.instrument.Instrument;
import pl.lukaszbany.coinbase.domain.price.data.PriceData;

import java.util.Optional;

public interface PriceDataRepository {

    Optional<PriceData> findByInstrument(Instrument instrument);

    void save(PriceData priceData);

}
