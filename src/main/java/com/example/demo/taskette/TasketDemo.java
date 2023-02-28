package com.example.demo.taskette;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.config.Task;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class TasketDemo {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Bean
    public Step stepOne(){
        return steps.get("StepOne")
                .tasklet(new TaskletOne())
                .build();
//        return new StepBuilder("step1", jobRepository)
//                .tasklet(tasklet, transactionManager)
//                .build();
    }

    @Bean
    public Step stepTwo(){
        return steps.get("StepTwo")
                .tasklet(new TaskletTwo())
                .build();
//        return new StepBuilder("step2", jobRepository)
//                .tasklet(tasklet, transactionManager)
//                .build();
    }

    @Bean
    public Job job(){
        return jobs.get("job")
                .start(stepOne())
                .next(stepTwo())
                .next(stepTwo())
                .build();

//        return new JobBuilder("job", jobRepository)
//                .start(step1)
//                .build();
    }

//    @Bean
//    public Tasklet tasklet(){
//        return new TaskletOne();
//    }
}
