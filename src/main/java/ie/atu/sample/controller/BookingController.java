package ie.atu.sample.controller;

import ie.atu.sample.dto.BookingRequest;
import ie.atu.sample.dto.BookingResponse;
import ie.atu.sample.exception.ExceedRoomCapacityException;
import ie.atu.sample.service.BookingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/booking")
    public BookingResponse createBooking(@Valid @RequestBody BookingRequest bookingRequest) throws ExceedRoomCapacityException {
        return bookingService.createBooking(bookingRequest);

    }
}