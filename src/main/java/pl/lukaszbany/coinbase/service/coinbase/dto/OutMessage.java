package pl.lukaszbany.coinbase.service.coinbase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class OutMessage {

    private String type;

    @JsonProperty("product_ids")
    private List<String> productIds;

    private List<Channel> channels;

}
