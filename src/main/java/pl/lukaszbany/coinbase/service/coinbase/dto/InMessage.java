package pl.lukaszbany.coinbase.service.coinbase.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class InMessage {

    @JsonProperty(required = true)
    private String type;

    private List<Channel> channels;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("best_bid")
    private String bestBid;

    @JsonProperty("best_ask")
    private String bestAsk;

    private String price;

    private String time;

}
