package main.application;

import java.util.List;

import main.core.Employee;
import main.core.EmployeeRepository;
import main.core.GreetingsMessage;
import main.core.GreetingsMessageSender;
import main.core.OurDate;

public class BirthdayService {

    private final GreetingsMessageSender greetingsMessageSender;
    private final EmployeeRepository employeeRepository;

    public BirthdayService(GreetingsMessageSender greetingsMessageSender,
            EmployeeRepository employeeRepository) {
        this.greetingsMessageSender = greetingsMessageSender;
        this.employeeRepository = employeeRepository;
    }

    public void sendGreetings(OurDate today) {
        List<Employee> employees = employeeRepository
                .findEmployeesWhoseBirthdayIs(today);

        for (Employee employee : employees) {
            sendGreetingsTo(employee);
        }
    }
    
    private void sendGreetingsTo(Employee employee) {
        GreetingsMessage greetingsMessage = new GreetingsMessage(employee);
        greetingsMessageSender.send(greetingsMessage);
    }
}
