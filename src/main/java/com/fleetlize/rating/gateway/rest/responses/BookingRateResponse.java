package com.fleetlize.rating.gateway.rest.responses;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingRateResponse {
  private Long id;
  private BigDecimal note;
}
