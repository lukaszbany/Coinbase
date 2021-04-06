package pl.lukaszbany.coinbase.service.coinbase.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel {

    private String name;

    @JsonProperty("product_ids")
    private List<String> productIds;
    
}
