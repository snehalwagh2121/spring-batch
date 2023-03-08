package com.example.demo.chunk.reader;

import com.example.demo.model.Employee;
import com.example.demo.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@StepScope
@Slf4j
public class ReaderClass implements ItemReader<List<Employee>> {

    @Value("${excel.file.location}")
    String filePath;

    ExcelUtil excelUtil= new ExcelUtil();

    @Override
    public List<Employee> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        log.info("executing reader");
        return (excelUtil.readExcelFile(filePath));

    }
}
