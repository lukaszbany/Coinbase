package pl.lukaszbany.coinbase.domain.instrument.repository;

import pl.lukaszbany.coinbase.domain.instrument.Instrument;

import java.util.Optional;
import java.util.Set;

public interface InstrumentRepository {

    Set<Instrument> findAll();

    Optional<Instrument> findByExternalId(String externalId);

}
