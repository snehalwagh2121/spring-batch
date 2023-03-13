package com.example.demo.chunk.reader;

import com.example.demo.model.SalesEmployeeCopy;
import com.example.demo.repo.SalesRepositoryCopy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class ReaderClass {

    @Autowired
    SalesRepositoryCopy salesRepository;

    @StepScope
    public ItemReader<SalesEmployeeCopy> readSalesTable() {
        log.info("reading sales copy records from DB");
        AtomicInteger i = new AtomicInteger(0);
        List<SalesEmployeeCopy> salesEmployeeList = (List<SalesEmployeeCopy>) salesRepository.findAll();
        log.info("sales objects from DB = " + salesEmployeeList.size());
        return (() -> i.get() >= salesEmployeeList.size() ? null : salesEmployeeList.get(i.getAndIncrement()));
    }

}
