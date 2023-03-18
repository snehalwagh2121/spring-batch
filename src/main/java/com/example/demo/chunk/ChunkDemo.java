package com.example.demo.chunk;

import com.example.demo.chunk.processor.ProcessorClass;
import com.example.demo.chunk.reader.ReaderClass;
import com.example.demo.chunk.writer.WriterClass;
import com.example.demo.model.Employee;
import com.example.demo.model.SalesEmployee;
import com.example.demo.model.SalesEmployeeCopy;
import com.example.demo.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@Slf4j
@RefreshScope
public class ChunkDemo {

    ExcelUtil excelUtil = new ExcelUtil();
    @Value("${excel.file.location}")
    String filePath;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private ProcessorClass processor;
    @Autowired
    private WriterClass writer;
    @Autowired
    private ReaderClass reader;

    @Bean
    public Job job() throws Exception {
        log.info("executing chunk job");
        return jobBuilderFactory.get("com.example.demo.chunk job")
                .flow(step1())
                .next(step2())
                .end()
                .build();
    }

    @Bean
    public Step step1() throws Exception {
        assert processor != null;
        assert writer != null;

        return stepBuilderFactory.get("step1")
                .<Employee, SalesEmployee>chunk(1)
                .reader(excelUtil.excelItemReader(filePath))
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Step step2() throws Exception {

        return stepBuilderFactory.get("step2")
                .<SalesEmployeeCopy, SalesEmployee>chunk(1)
                .reader(reader.readSalesTable())
                .writer(items -> writer.writeIntoFile(items)) //no processor required for this
                .build();
    }

}
