package com.example.demo.util;

import com.example.demo.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExcelUtil {

    public List<Employee> readExcelFile(String fileLocation) {
        log.info("file location : " + fileLocation);
        List<Employee> employeeList = new ArrayList<>();
        try {
            //open file
            FileInputStream file = new FileInputStream(new File(fileLocation));
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //read each sheet
            Sheet sheet = workbook.getSheetAt(0);
            int i = 0;
            int nameCell = 0, joiningDateCell = 0, emailAddCell = 0, deptCell = 0, monthlySalCell = 0, jobStatCell = 0;
            for (Row row : sheet) {
                if (i == 0) {
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        log.info("cell header value: " + row.getCell(j));
                        switch (row.getCell(j).toString()) {
                            case "Name":
                                nameCell = j;
                                break;
                            case "Joining Date":
                                joiningDateCell = j;
                                break;
                            case "Email Address":
                                emailAddCell = j;
                                break;
                            case "Department":
                                deptCell = j;
                                break;
                            case "Monthly Salary":
                                monthlySalCell = j;
                                break;
                            case "Job Status":
                                jobStatCell = j;
                                break;
                        }
                    }
                    i++;
                    continue;
                }
                employeeList.add(new Employee()
                        .name(row.getCell(nameCell).toString())
                        .joining_date(row.getCell(joiningDateCell).toString())
                        .email_addr(row.getCell(emailAddCell).toString())
                        .monthly_salary(row.getCell(monthlySalCell).toString())
                        .dept(row.getCell(deptCell).toString())
                        .job_status(row.getCell(jobStatCell).toString()));

                i++;
            }
        } catch (FileNotFoundException e) {
            log.info("file not found at location : " + fileLocation);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}
