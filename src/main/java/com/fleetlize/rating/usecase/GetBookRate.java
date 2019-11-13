package com.fleetlize.rating.usecase;

import com.fleetlize.rating.entities.Rate;
import com.fleetlize.rating.gateway.data.entities.RateEntity;
import com.fleetlize.rating.gateway.data.repositories.BookingRepository;
import com.fleetlize.rating.gateway.data.repositories.RatingRepository;
import com.fleetlize.rating.usecase.mapper.RatingMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetBookRate {

  private BookingRepository bookingRepository;
  private RatingRepository ratingRepository;
  private RatingMapper ratingMapper;

  public Rate execute(final Rate booking) {
    final RateEntity rateEntity = bookingRepository.findById(booking.getBooking().getId())
        .map(b -> ratingRepository.save(RateEntity.builder().booking(b).score(booking.getScore()).build())).orElseThrow();

    return ratingMapper.from(rateEntity);
  }

}
