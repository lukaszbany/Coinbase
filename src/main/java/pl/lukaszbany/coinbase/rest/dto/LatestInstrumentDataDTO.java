package pl.lukaszbany.coinbase.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Time;

@Getter
@Builder
@AllArgsConstructor
public class LatestInstrumentDataDTO {

    private String instrument;

    private BigDecimal bid;

    private BigDecimal ask;

    private BigDecimal last;

    private Time time;

}
