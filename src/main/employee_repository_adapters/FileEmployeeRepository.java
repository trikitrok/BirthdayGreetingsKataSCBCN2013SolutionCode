package main.employee_repository_adapters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import main.core.Employee;
import main.core.EmployeeRepository;
import main.core.EmployeesRepositoryAccessException;
import main.core.GreetingsMessage;
import main.core.OurDate;

public class FileEmployeeRepository implements EmployeeRepository {

    private final String employeesFilePath;

    public List<Employee> findEmployeesWhoseBirthdayIs(OurDate today) {

        List<Employee> employees = getEmployees();

        List<Employee> employeesWhoseBirthdayIsToday = new ArrayList<Employee>();

        for (Employee employee : employees) {
            if (employee.isBirthday(today)) {
                employeesWhoseBirthdayIsToday.add(employee);
            }
        }

        return employeesWhoseBirthdayIsToday;
    }

    public FileEmployeeRepository(String employeesFilePath) {
        this.employeesFilePath = employeesFilePath;
    }

    private List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<Employee>();

        try {
            BufferedReader in = new BufferedReader(new FileReader(
                    employeesFilePath));
            String line = in.readLine(); // skip header

            while ((line = in.readLine()) != null) {
                Employee employee = obtainEmployee(line);
                employees.add(employee);
            }
        } catch (IOException ioException) {
            throw new EmployeesRepositoryAccessException();
        }

        return employees;
    }

    private Employee obtainEmployee(String line) {
        try {
            String[] employeeData = line.split(", ");
            return new Employee(employeeData[1], employeeData[0],
                    employeeData[2], employeeData[3]);
        } catch (ParseException parseException) {
            throw new EmployeesRepositoryAccessException();
        }
    }
}
