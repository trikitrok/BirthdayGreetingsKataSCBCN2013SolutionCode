package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;

import org.junit.Before;
import org.junit.Test;

import main.core.BirthdayService;
import main.core.EmployeeRepository;
import main.core.OurDate;
import main.employee_repository_adapters.FileEmployeeRepository;
import main.message_sender_adapters.GreetingsEmailSender;

public class AcceptanceTest {

    private List<Message> messagesSent;
    private BirthdayService service;
    private GreetingsEmailSender greetingsMessageSender;
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() throws Exception {
        final int SMTP_PORT = 25;
        final String SENDER = "sender@here.com";
        String SMTP_HOST = "localhost";

        messagesSent = new ArrayList<Message>();
        greetingsMessageSender = new GreetingsEmailSender(SMTP_HOST, SMTP_PORT,
                SENDER) {
            @Override
            protected void sendMessage(Message msg) {
                messagesSent.add(msg);
            }
        };

        employeeRepository = new FileEmployeeRepository(
                "src/test/resources/employee_data.txt");

        service = new BirthdayService(greetingsMessageSender,
                employeeRepository);
    }

    @Test
    public void baseScenario() throws Exception {

        service.sendGreetings(new OurDate("2008/10/08"));

        assertEquals("message not sent?", 1, messagesSent.size());
        Message message = messagesSent.get(0);
        assertEquals("Happy Birthday, dear John!", message.getContent());
        assertEquals("Happy Birthday!", message.getSubject());
        assertEquals(1, message.getAllRecipients().length);
        assertEquals("john.doe@foobar.com",
                message.getAllRecipients()[0].toString());
    }

    @Test
    public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
        service.sendGreetings(new OurDate("2008/01/01"));

        assertEquals("what? messages?", 0, messagesSent.size());
    }
}
