package com.fleetlize.rating.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Booking {
  private Long id;
  private String carPlate;
}
