package com.fleetlize.rating.gateway.jms;

import com.fleetlize.rating.exceptions.CarAlreadyExists;
import com.fleetlize.rating.gateway.jms.incoming.CarCreation;
import com.fleetlize.rating.gateway.jms.mapper.JmsCarMapper;
import com.fleetlize.rating.usecase.CreateCar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(Sink.class)
public class CarCreationListener {

    private JmsCarMapper jmsCarMapper;
    private CreateCar createCar;

    public CarCreationListener(final JmsCarMapper jmsCarMapper,
                               final CreateCar createCar) {
        this.jmsCarMapper = jmsCarMapper;
        this.createCar = createCar;
    }

    @StreamListener(Sink.INPUT)
    public void messageReceived(final CarCreation carCreation) {
        log.info("car creation notified {}", carCreation);

        try {
            final var carCreated = createCar.execute(jmsCarMapper.from(carCreation));
            log.debug("car created {}", carCreated);
        } catch (final CarAlreadyExists e) {
            log.error("car already exists", e);
        }
    }

}
