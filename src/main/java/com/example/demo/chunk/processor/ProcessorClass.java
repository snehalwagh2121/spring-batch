package com.example.demo.chunk.processor;

import com.example.demo.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@StepScope
@Slf4j
public class ProcessorClass implements ItemProcessor<List<Employee>, Employee> {

    @Override
    public Employee process(List<Employee> employeeList) throws Exception {
        log.info("processing employee size:: " + employeeList.size());
        for(Employee employee: employeeList){
            if (employee.getDept().equals("Sales"))
            {
                log.info("employee of sales dept");
                return employee;
            }
        }
        log.info("employee not of sales dept");
        return null;
    }

}
