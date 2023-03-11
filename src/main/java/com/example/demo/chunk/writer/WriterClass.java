package com.example.demo.chunk.writer;

import com.example.demo.model.SalesEmployee;
import com.example.demo.repo.SalesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@StepScope
@Slf4j
public class WriterClass implements ItemWriter<SalesEmployee> {

    @Autowired
    SalesRepository salesRepository;

    @Override
    public void write(List<? extends SalesEmployee> list) throws Exception {
        log.info("writing employees to file");
        salesRepository.saveAll(list);
        log.info("writing done");
    }
}
