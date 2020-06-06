package webApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webApp.entities.Employee;
import webApp.entities.dto.CustomResponseDto;
import webApp.entities.dto.LoginrequestDto;
import webApp.repositories.EmployeeRepository;
import webApp.utils.UtilsClass;
import webBase.service.ServiceBase;

import javax.servlet.http.HttpSession;

/**
 * Created by Sarim on 5/5/2020.
 */
@Service
public class EmployeeService extends ServiceBase<Employee, Long> {

    private EmployeeRepository employeeRepository;
    private HttpSession session;

    @Autowired
    public EmployeeService(EmployeeRepository repository , HttpSession session1) {
        super(repository);
        employeeRepository = repository;
        session = session1;
    }

    public Employee getEmployeeByCode(Long empCode){
        return employeeRepository.findByEmployeeCode(empCode);
    }

    public CustomResponseDto empLogIn(LoginrequestDto loginrequestDto) {
        CustomResponseDto customResponseDto = new CustomResponseDto();
        Employee employee = employeeRepository.findByEmployeeEmailAndEmployeePassword(loginrequestDto.getEmail(), loginrequestDto.getPassword());
        if (employee != null) {
            session.setAttribute("EMP_NAME",employee.getEmployeeName());
            customResponseDto.setResponseCode("200");
            customResponseDto.setStatus("Login");
            customResponseDto.setMessage("Login Successfully!");
            customResponseDto.setOuthToken(UtilsClass.generateOauthkey());
            customResponseDto.setEntityClass(employee);
        } else {
            customResponseDto.setResponseCode("400");
            customResponseDto.setStatus("UnAuthorized");
            customResponseDto.setMessage("Invalid Email and Password!");
        }
        return customResponseDto;
    }

    public CustomResponseDto saveEmployee(Employee employee) {
        CustomResponseDto customResponseDto = new CustomResponseDto();
        Employee emp = employeeRepository.findByEmployeeEmail(employee.getEmployeeEmail());
        Employee emp1 = employeeRepository.findByEmployeeCode(employee.getEmployeeCode());
        if (emp != null) {
            customResponseDto.setResponseCode("401");
            customResponseDto.setStatus("exists");
            customResponseDto.setMessage("Employee already registered with that email");
        } else if (emp1 != null) {
            customResponseDto.setResponseCode("401");
            customResponseDto.setStatus("exists");
            customResponseDto.setMessage("Employee already registered with that employee code");
        }
        employeeRepository.save(employee);
        customResponseDto.setResponseCode("201");
        customResponseDto.setStatus("created");
        customResponseDto.setMessage("Registered Successfully!");
        return customResponseDto;
    }

}
