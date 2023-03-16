package com.example.demo.chunk.writer;

import com.example.demo.model.SalesEmployee;
import com.example.demo.repo.SalesRepository;
import com.example.demo.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RefreshScope
public class WriterClass implements ItemWriter<SalesEmployee> {

    @Autowired
    SalesRepository salesRepository;
    ExcelUtil excelUtil = new ExcelUtil();
    @Value("${com.example.demo.chunk.excel.file.path}")
    private String fileLocation;

    @Override
    public void write(List<? extends SalesEmployee> list) throws Exception {
        log.info("writing employees to file");
        salesRepository.saveAll(list);
        log.info("writing done");
    }

    @StepScope
    public void writeIntoFile(List<? extends SalesEmployee> list) {
        excelUtil.writeIntoExcelFile(fileLocation, list);
    }
}
