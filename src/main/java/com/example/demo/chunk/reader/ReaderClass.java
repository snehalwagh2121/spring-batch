package com.example.demo.chunk.reader;

import com.example.demo.model.Employee;
import org.springframework.batch.item.file.FlatFileItemReader;

//@Component
//@Slf4j
public class ReaderClass extends FlatFileItemReader<FlatFileItemReader<Employee>> {

//    @Value("${excel.file.location}")
//    String filePath;
//
//    public FlatFileItemReader<Employee> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//        log.info("executing reader");
//        DelimitedLineTokenizer delimitedLineTokenizer= new DelimitedLineTokenizer();
//        delimitedLineTokenizer.setDelimiter(",");
//        delimitedLineTokenizer.setStrict(false);
//        delimitedLineTokenizer.setNames("Names","Joining Date","Email Address","Department","Monthly Salary","Job Status");
//
//        BeanWrapperFieldSetMapper<Employee> fieldSetMapper= new BeanWrapperFieldSetMapper<>();
//        fieldSetMapper.setTargetType(Employee.class);
//
//        DefaultLineMapper<Employee> defaultLineMapper = new DefaultLineMapper<>();
//        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
//        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
//
//        FlatFileItemReader<Employee> reader = new FlatFileItemReader<>();
//        reader.setName("reader");
//        reader.setResource(new FileSystemResource(filePath));
//        reader.setLineMapper(defaultLineMapper);
//        reader.setLinesToSkip(1);
//        return reader;
//
////
////
////        return new FlatFileItemReaderBuilder<Employee>()
////                .name("empReader")
////                .resource(new ClassPathResource(filePath))
////                .delimited()
////                .names("Name","Joining Date", "Email Address","Department","Monthly Salary","Job Status")
////                .fieldSetMapper(new BeanWrapperFieldSetMapper<Employee>(){{
////                    setTargetType(Employee.class);
////                }})
////                .build();
//
//    }
}
