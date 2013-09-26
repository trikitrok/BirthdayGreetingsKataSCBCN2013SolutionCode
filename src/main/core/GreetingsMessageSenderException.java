package main.core;

public class GreetingsMessageSenderException extends RuntimeException {

    public static final String ADDRESS_PROBLEM = "Problem with recipient addres";
    public static final String CREATION_PROBLEM = "Problem creating message";
    public static final String SENDING_PROBLEM = "Problem sending message";

    private static final long serialVersionUID = 1L;

    public GreetingsMessageSenderException(String message) {
        super(message);
    }
}
