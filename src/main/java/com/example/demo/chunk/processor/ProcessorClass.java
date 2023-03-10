package com.example.demo.chunk.processor;

import com.example.demo.model.Employee;
import com.example.demo.model.SalesEmployee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@StepScope
@Slf4j
public class ProcessorClass implements ItemProcessor<Employee, SalesEmployee> {

    @Override
    public SalesEmployee process(Employee employee) throws Exception {
        log.info("processing employee :: " + employee);
        if (employee.getDept().equals("Sales"))
        {
            log.info("employee of sales dept");
            return new SalesEmployee()
                    .name(employee.getName())
                    .dept(employee.getDept())
                    .email_addr(employee.getEmail_addr())
                    .job_status(employee.getJob_status())
                    .joining_date(employee.getJoining_date())
                    .monthly_salary(employee.getMonthly_salary());
        }
        log.info("employee not of sales dept");
        return null;
    }

}
