package webApp.repositories;

import webApp.entities.Employee;
import webBase.repository.RepositoryBase;

/**
 * Created by Sarim on 5/1/2020.
 */
public interface EmployeeRepository extends RepositoryBase<Employee, Long> {
    Employee findByEmployeeEmail(String email);
    Employee findByEmployeeCode(Long code);
    Employee findByEmployeeEmailAndEmployeePassword(String email , String password);
}
