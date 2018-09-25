package lesson4.homework4_1.exception;

public class InternalServerError extends Exception {
    private String methodLoc;

    public InternalServerError(String methodLoc, String message) {
        super(message);
        this.methodLoc = methodLoc;
    }

    public String getMethodLoc() {
        return methodLoc;
    }
}
