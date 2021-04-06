package pl.lukaszbany.coinbase.service.coinbase.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Channels {

    TICKER("ticker");

    private final String name;

}
