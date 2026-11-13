package ie.atu.sample.dto;

import ie.atu.sample.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
    private long bookingID;

    private String organiserName;

    private int attendeeCount;

    private long roomID;

    private Status status = Status.ACTIVE;
}
