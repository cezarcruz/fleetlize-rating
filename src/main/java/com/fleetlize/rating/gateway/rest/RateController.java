package com.fleetlize.rating.gateway.rest;

import com.fleetlize.rating.entities.Booking;
import com.fleetlize.rating.entities.Rate;
import com.fleetlize.rating.gateway.rest.mappers.RateRestMapper;
import com.fleetlize.rating.gateway.rest.request.RateRequest;
import com.fleetlize.rating.gateway.rest.responses.RateResponse;
import com.fleetlize.rating.usecase.GetBookRate;
import com.fleetlize.rating.usecase.RateCar;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/rate")
public class RateController {

  private RateCar rateCar;
  private RateRestMapper rateRestMapper;
  private GetBookRate getBookRate;

  @PostMapping
  public ResponseEntity<RateResponse> rate(final RateRequest rateRequest) {
    final var rate = rateRestMapper.from(rateRequest);
    final var rated = rateCar.execute(rate);
    return ResponseEntity.status(HttpStatus.CREATED).body(rateRestMapper.from(rated));
  }

  @GetMapping("/{bookingId}")
  public ResponseEntity<RateResponse> rate(@PathVariable final Long bookingId) {
    final var bookingRate = getBookRate.execute(
        Rate.builder().booking(Booking.builder().id(bookingId).build()).build());
    final var from = rateRestMapper.from(bookingRate);
    return ResponseEntity.ok(from);
  }
}
