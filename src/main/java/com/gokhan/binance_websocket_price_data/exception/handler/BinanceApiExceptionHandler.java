package com.gokhan.binance_websocket_price_data.exception.handler;

import com.gokhan.binance_websocket_price_data.api.model.response.ErrorResponse;
import com.gokhan.binance_websocket_price_data.exception.JsonProcessingExceptionWrapper;
import com.gokhan.binance_websocket_price_data.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BinanceApiExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleNotFoundException(
      final NotFoundException exception) {
    return getBinanceErrorResponse(exception);
  }

  @ExceptionHandler(JsonProcessingExceptionWrapper.class)
  public ResponseEntity<ErrorResponse> handleJsonProcessingException(
      JsonProcessingExceptionWrapper e) {
    return ResponseEntity.internalServerError()
        .body(new ErrorResponse(e.getMessage()));
  }

  private ResponseEntity<ErrorResponse> getBinanceErrorResponse(
      NotFoundException e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorResponse(e.getMessage()));
  }

}
