package pl.lukaszbany.coinbase.domain.instrument;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Instrument {

    private String name;

    private String externalId;

}
