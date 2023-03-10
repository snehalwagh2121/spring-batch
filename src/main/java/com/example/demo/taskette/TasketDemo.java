package com.example.demo.taskette;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@EnableBatchProcessing
public class TasketDemo {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private TaskletOne taskletOne;

    @Autowired
    private TaskletTwo taskletTwo;

    @Bean
    public Step stepOne() {
        return steps.get("StepOne")
                .tasklet(taskletOne)
                .build();
    }

    @Bean
    public Step stepTwo() {
        return steps.get("StepTwo")
                .tasklet(taskletTwo)
                .build();
    }

    @Bean
    public Job taskletJob() {
        log.info("executing chunk job");
        return jobs.get("job")
                .start(stepOne())
                .next(stepTwo())
                .build();
    }
}
