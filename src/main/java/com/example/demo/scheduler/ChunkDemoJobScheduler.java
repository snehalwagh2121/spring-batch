package com.example.demo.scheduler;

import com.example.demo.chunk.ChunkDemo;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ChunkDemoJobScheduler {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    ChunkDemo chunkDemo;

    @Scheduled(fixedRate = 5000)
    public void run() throws Exception {
        JobExecution execution = jobLauncher.run(
                chunkDemo.job(),
                new JobParametersBuilder().toJobParameters()
        );
    }
}
