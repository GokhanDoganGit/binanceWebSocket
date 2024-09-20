package com.gokhan.binance_websocket_price_data.api.model.response;

import static com.gokhan.binance_websocket_price_data.constants.ErrorMessage.BAD_REQUEST;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@JsonInclude(Include.NON_EMPTY)
public record ErrorResponse(
    @Schema(example = BAD_REQUEST)
    String message
) {

}
