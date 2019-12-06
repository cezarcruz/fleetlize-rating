package com.fleetlize.rating.gateway.jms;

import com.fleetlize.rating.gateway.jms.incoming.BookCreation;
import com.fleetlize.rating.gateway.jms.mapper.JmsBookMapper;
import com.fleetlize.rating.usecase.CreateBook;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class BookCreationListener {

  private CreateBook createBook;
  private JmsBookMapper jmsBookMapper;

  public void messageReceived(final BookCreation bookCreation) {
    log.info("book creation notified {}", bookCreation);

    final var booking = createBook.execute(jmsBookMapper.from(bookCreation));
    log.debug("booking created {}", booking);

  }

}
