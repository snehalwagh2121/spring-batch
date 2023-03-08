package com.example.demo.chunk.writer;

import com.example.demo.model.Employee;
import com.example.demo.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@StepScope
@Slf4j
public class WriterClass implements ItemWriter<Employee> {

    ExcelUtil excelUtil= new ExcelUtil();

    @Value("${com.example.demo.chunk.excel.file.path}")
    private String filepath;

    @Override
    public void write(List<? extends Employee> list) throws Exception {
        log.info("writing employees to file");
        excelUtil.writeIntoExcelFile(filepath, (List<Employee>) list);
        log.info("writing done");
    }
}
