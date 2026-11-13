package ie.atu.sample.service;

import ie.atu.sample.client.RoomClient;
import ie.atu.sample.dto.BookingRequest;
import ie.atu.sample.dto.BookingResponse;
import ie.atu.sample.dto.RoomResponse;
import ie.atu.sample.exception.BookingNotFoundException;
import ie.atu.sample.exception.ExceedRoomCapacityException;
import ie.atu.sample.model.Booking;
import ie.atu.sample.model.Status;
import ie.atu.sample.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingService {

    private final RoomClient roomClient;
    private final BookingRepository bookingRepository;

    public BookingResponse createBooking(BookingRequest bookingRequest) {
        RoomResponse roomResponse = roomClient.getRoomById(bookingRequest.getRoomID());
        if (roomResponse.getCapacity() < bookingRequest.getAttendeeCount()) {
            throw new ExceedRoomCapacityException(
                    "Attendee count exceeds room capacity"
            );
        }
        Booking booking = new Booking();
        booking.setRoomID(bookingRequest.getRoomID());
        booking.setAttandeeCount(bookingRequest.getAttendeeCount());
        booking.setOrganiserName(bookingRequest.getOrganiserName());
        booking.setStatus(Status.ACTIVE);
        bookingRepository.save(booking);

        return getBookingResponse(booking);
    }

    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(
                        "Booking not found with ID: " + id
                ));

        return getBookingResponse(booking);
    }

    private BookingResponse getBookingResponse(Booking booking) {
        BookingResponse bookingResponse = new BookingResponse();
        bookingResponse.setBookingID(booking.getId());
        bookingResponse.setStatus(booking.getStatus());
        bookingResponse.setRoomID(booking.getRoomID());
        bookingResponse.setAttendeeCount(booking.getAttandeeCount());
        bookingResponse.setOrganiserName(booking.getOrganiserName());

        return bookingResponse;
    }
}
