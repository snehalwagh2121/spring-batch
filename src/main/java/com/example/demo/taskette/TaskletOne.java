package com.example.demo.taskette;

import com.example.demo.model.Employee;
import com.example.demo.service.TaskletService;
import com.example.demo.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class TaskletOne implements Tasklet, StepExecutionListener {

    private final Logger logger = LoggerFactory
            .getLogger(TaskletOne.class);

    ExcelUtil excelUtil = new ExcelUtil();

    @Value("${excel.file.location}")
    String fileLocation;

    @Autowired
    TaskletService taskletService;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.info("filelocaltion: " + fileLocation);
        logger.info("Lines Reader initialized.");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("tasklet 1 start");

        List<Employee> employeeList = excelUtil.readExcelFile(fileLocation);
        log.info("employee list size: " + employeeList.size());
        taskletService.insertInDb(employeeList);

        System.out.println("tasklet 1 end");
        return RepeatStatus.FINISHED;
    }
}
