package com.example.demo.taskette;

import com.example.demo.model.Employee;
import com.example.demo.service.TaskletService;
import com.example.demo.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RefreshScope
public class TaskletTwo implements Tasklet {
    @Autowired
    TaskletService service;
    @Value("${write.excel.file.path}")
    String filepath;

    ExcelUtil excelUtil = new ExcelUtil();

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("tasklet 2 start");
        try{
            List<Employee> employeeList = service.getEmployeesFromDB();
            log.info("employees got from DB : " + employeeList.size());
            log.info("writing into excel file now");
            excelUtil.writeIntoExcelFile(filepath, employeeList);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        log.info("tasklet 2 end");
        return RepeatStatus.FINISHED;
    }
}
