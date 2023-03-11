package com.example.demo.chunk;

import com.example.demo.chunk.processor.ProcessorClass;
import com.example.demo.chunk.writer.WriterClass;
import com.example.demo.model.Employee;
import com.example.demo.model.SalesEmployee;
import com.example.demo.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@Slf4j
public class ChunkDemo {

    ExcelUtil excelUtil = new ExcelUtil();
    @Value("${excel.file.location}")
    String filePath;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    //    @Autowired
//    private ReaderClass reader;
    @Autowired
    private ProcessorClass processor;
    @Autowired
    private WriterClass writer;

    @Bean
    public Job job() throws Exception {
        log.info("executing chunk job");
        return jobBuilderFactory.get("com.example.demo.chunk job")
                .flow(chunkStep())
                .end()
                .build();
    }

    @Bean
    public Step chunkStep() throws Exception {
        assert processor != null;
        assert writer != null;

        return stepBuilderFactory.get("step1")
                .<Employee, SalesEmployee>chunk(10)
                .reader(excelUtil.excelItemReader(filePath))
                .processor(processor)
                .writer(writer)
                .build();
    }

}
