package sg.edu.nus.iss.workshop23Redo.service;

public class BffException extends Exception {

    private String reason;

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public BffException(String reason) {
        this.reason = reason;
    }

    
}
