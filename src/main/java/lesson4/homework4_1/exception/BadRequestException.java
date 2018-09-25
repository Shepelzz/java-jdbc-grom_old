package lesson4.homework4_1.exception;

public class BadRequestException extends RuntimeException {
    private String methodLoc;

    public BadRequestException(String methodLoc, String message) {
        super(message);
        this.methodLoc = methodLoc;
    }

    public String getMethodLoc() {
        return methodLoc;
    }
}
