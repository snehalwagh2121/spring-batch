package com.example.demo.scheduler;

import com.example.demo.chunk.ChunkDemo;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class ChunkDemoJobScheduler {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    ChunkDemo chunkDemo;

    @EventListener(ApplicationReadyEvent.class)
    public void run() throws Exception {
        jobLauncher.run(
                chunkDemo.job(),
                new JobParametersBuilder().toJobParameters()
        );
    }
}
