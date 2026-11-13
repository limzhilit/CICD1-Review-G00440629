package ie.atu.sample.exception;

public class ExceedRoomCapacityException extends RuntimeException {
    public ExceedRoomCapacityException(String message) {
        super(message);
    }
}