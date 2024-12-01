package net.thevpc.samples.springnuts.modulea.ws;

import net.thevpc.samples.springnuts.modulea.model.Employee;
import net.thevpc.samples.springnuts.modulea.service.api.HrModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeWS {
    @Autowired
    private HrModule hrModule;
    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee) {
        hrModule.addEmployee(employee);
        return employee;
    }
}
