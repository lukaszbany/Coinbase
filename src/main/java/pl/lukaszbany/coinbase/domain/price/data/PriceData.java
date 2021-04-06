package pl.lukaszbany.coinbase.domain.price.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.lukaszbany.coinbase.domain.instrument.Instrument;

import java.math.BigDecimal;
import java.sql.Time;

@Builder
@Getter
@AllArgsConstructor
public class PriceData {

    private Instrument instrument;

    private BigDecimal bid;

    private BigDecimal ask;

    private BigDecimal last;

    private Time time;

}
