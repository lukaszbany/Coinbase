package pl.lukaszbany.coinbase.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lukaszbany.coinbase.rest.dto.LatestInstrumentDataDTO;
import pl.lukaszbany.coinbase.rest.mapper.LatestInstrumentDataDtoMapper;

import java.util.List;

@RestController
public class PriceDataEndpoint {

    private final LatestInstrumentDataDtoMapper latestInstrumentDataDtoMapper;

    @Autowired
    public PriceDataEndpoint(LatestInstrumentDataDtoMapper latestInstrumentDataDtoMapper) {
        this.latestInstrumentDataDtoMapper = latestInstrumentDataDtoMapper;
    }

    @GetMapping("/instruments/latest-prices")
    public List<LatestInstrumentDataDTO> getLatestInstrumentDataDTO() {
        return latestInstrumentDataDtoMapper.getLatestInstrumentsDataDTO();
    }
}
