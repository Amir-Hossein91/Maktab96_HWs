package repository.impl;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.Employee;

public class EmployeeRepositoryImpl extends BaseRepositoryImpl<Employee>{
    public EmployeeRepositoryImpl() {
        super(Employee.class);
    }
}
