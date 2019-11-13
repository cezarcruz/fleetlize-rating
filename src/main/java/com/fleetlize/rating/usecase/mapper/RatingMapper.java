package com.fleetlize.rating.usecase.mapper;

import com.fleetlize.rating.entities.Rate;
import com.fleetlize.rating.gateway.data.entities.RateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingMapper {
  Rate from(final RateEntity rateEntity);
}
