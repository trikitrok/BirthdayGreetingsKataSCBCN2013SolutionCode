package main.core;

import java.util.List;

public class BirthdayService {

	private final GreetingsMessageSender greetingsMessageSender;
	private final EmployeeRepository employeeRepository;

	public BirthdayService(GreetingsMessageSender greetingsMessageSender, EmployeeRepository employeeRepository) {
		this.greetingsMessageSender = greetingsMessageSender;
		this.employeeRepository = employeeRepository;
	}

	public void sendGreetings(OurDate today) {
		List<Employee> employees = employeeRepository.findEmployeesWhoseBirthdayIs(today);
		
		for (Employee employee : employees) {
    		GreetingsMessage greetingsMessage = new GreetingsMessage(employee);
    		greetingsMessageSender.send(greetingsMessage);
        }
	}
}
