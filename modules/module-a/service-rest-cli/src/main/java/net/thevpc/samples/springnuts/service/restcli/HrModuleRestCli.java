package net.thevpc.samples.springnuts.service.restcli;

import net.thevpc.samples.springnuts.modulea.model.Employee;
import net.thevpc.samples.springnuts.modulea.service.api.HrModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HrModuleRestCli implements HrModule {
    @Autowired
    private RestTemplate restTemplate;
    private String url = "http://localhost:8080";

    @Override
    public Employee addEmployee(Employee employee) {
        return restTemplate.postForObject(
                url + "/employee",
                employee,
                Employee.class
        );
    }
}
