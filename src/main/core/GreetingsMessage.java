package main.core;

public class GreetingsMessage {

    private final String subject;
    private final String body;
    private final String recipient;

    public GreetingsMessage(Employee employee) {
        this.recipient = employee.getEmail();
        this.body = "Happy Birthday, dear %NAME%!".replace("%NAME%",
                employee.getFirstName());
        this.subject = "Happy Birthday!";
    }

    public final String getSubject() {
        return subject;
    }

    public final String getBody() {
        return body;
    }

    public final String getRecipient() {
        return recipient;
    }

}
