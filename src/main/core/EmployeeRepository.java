package main.core;

import java.util.List;

public interface EmployeeRepository {
	public List<Employee> findEmployeesWhoseBirthdayIs(OurDate today);
}
