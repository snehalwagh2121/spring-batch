package com.example.demo.chunk;

import com.example.demo.chunk.processor.ProcessorClass;
import com.example.demo.chunk.reader.ReaderClass;
import com.example.demo.chunk.writer.WriterClass;
import com.example.demo.model.Employee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableBatchProcessing
public class ChunkDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ReaderClass reader;
    @Autowired
    private ProcessorClass processor;
    @Autowired
    private WriterClass writer;

    @Bean
    public Job job(){
        return jobBuilderFactory.get("com.example.demo.chunk job")
                .start(chunkStep())
                .build();
    }

    @Bean
    public Step chunkStep() {
        return stepBuilderFactory.get("step1")
                .<List<Employee>, Employee>chunk(100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
