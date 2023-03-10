package com.example.demo.chunk;

import com.example.demo.chunk.processor.ProcessorClass;
import com.example.demo.chunk.reader.ReaderClass;
import com.example.demo.chunk.writer.WriterClass;
import com.example.demo.model.Employee;
import com.example.demo.model.SalesEmployee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Objects;

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
    public Job job() throws Exception {
        return jobBuilderFactory.get("com.example.demo.chunk job")
                .start(chunkStep())
                .build();
    }

    @Bean
    public Step chunkStep() throws Exception {
        assert reader!=null;
        assert processor!=null;
        assert writer!=null;

        return stepBuilderFactory.get("step1")
                .<Employee, SalesEmployee>chunk(10)
                .reader(Objects.requireNonNull(reader.read()))
                .processor(processor)
                .writer(writer)
                .build();
    }

}
