package com.example.demo.taskette;

import com.example.demo.repo.TaskletRepository;
import com.example.demo.service.TaskletService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
    public Job job() {
        return jobs.get("job")
                .start(stepOne())
                .next(stepTwo())
                .build();
    }
}
