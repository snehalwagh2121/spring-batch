package com.example.demo.chunk.reader;

import com.example.demo.model.Employee;
import com.example.demo.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
@Slf4j
public class ReaderClass extends FlatFileItemReader<FlatFileItemReader<Employee>> {

    @Value("${excel.file.location}")
    String filePath;

    public FlatFileItemReader<Employee> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        log.info("executing reader");

        DefaultLineMapper<Employee> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(new DelimitedLineTokenizer());
        defaultLineMapper.setFieldSetMapper(new FieldSetMapper<Employee>() {

            @Override
            public Employee mapFieldSet(FieldSet fieldSet) throws BindException {
                Employee emp = new Employee();
                emp.setName(fieldSet.readString(0));
                emp.setJoining_date(fieldSet.readString(1));
                emp.setEmail_addr(fieldSet.readString(2));
                emp.setDept(fieldSet.readString(3));
                emp.setMonthly_salary(fieldSet.readString(4));
                emp.setJob_status(fieldSet.readString(5));
                return emp;
            }
        });
        defaultLineMapper.afterPropertiesSet();
        FlatFileItemReader<Employee> reader = new FlatFileItemReader<>();
        reader.setLineMapper(defaultLineMapper);
        reader.setResource(new ClassPathResource(filePath));
        reader.afterPropertiesSet();
        return reader;

//
//
//        return new FlatFileItemReaderBuilder<Employee>()
//                .name("empReader")
//                .resource(new ClassPathResource(filePath))
//                .delimited()
//                .names("Name","Joining Date", "Email Address","Department","Monthly Salary","Job Status")
//                .fieldSetMapper(new BeanWrapperFieldSetMapper<Employee>(){{
//                    setTargetType(Employee.class);
//                }})
//                .build();

    }
}
