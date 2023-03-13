package com.example.demo.util;

import com.example.demo.model.Employee;
import com.example.demo.model.SalesEmployeeCopy;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.item.ItemReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ExcelUtil {

    int nameCell = 0, joiningDateCell = 0, emailAddCell = 0, deptCell = 0, monthlySalCell = 0, jobStatCell = 0;

    public List<Employee> readExcelFile(String fileLocation) {
        log.info("file location : " + fileLocation);
        List<Employee> employeeList = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File(fileLocation));
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            Sheet sheet = workbook.getSheetAt(0);
            int i = 0;
            for (Row row : sheet) {
                if (i == 0) {
                    assignCellValues(row);
                    i++;
                    continue;
                }
                addEmployeeToEmpList(employeeList, row);
                i++;
            }
            file.close();
        } catch (FileNotFoundException e) {
            log.info("file not found at location : " + fileLocation);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public ItemReader<Employee> excelItemReader(String fileLocation) {
        log.info("file location : " + fileLocation);
        List<Employee> employeeList = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File(fileLocation));
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            Sheet sheet = workbook.getSheetAt(0);
            int i = 0;
            for (Row row : sheet) {
                if (i == 0) {
                    assignCellValues(row);
                    i++;
                    continue;
                }
                addEmployeeToEmpList(employeeList, row);
                i++;
            }
            file.close();
        } catch (FileNotFoundException e) {
            log.info("file not found at location : " + fileLocation);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        var index = new AtomicInteger(0);
        return () -> index.get() >= employeeList.size() ? null : employeeList.get(index.getAndIncrement());
    }

    private void addEmployeeToEmpList(List<Employee> employeeList, Row row) {
        employeeList.add(new Employee()
                .name(row.getCell(nameCell).toString())
                .joining_date(row.getCell(joiningDateCell).toString())
                .email_addr(row.getCell(emailAddCell).toString())
                .monthly_salary(row.getCell(monthlySalCell).toString())
                .dept(row.getCell(deptCell).toString())
                .job_status(row.getCell(jobStatCell).toString()));
    }

    private void assignCellValues(Row row) {
        for (int j = 0; j < row.getLastCellNum(); j++) {
            log.info("cell header value: " + row.getCell(j));
            switch (row.getCell(j).toString()) {
                case Constants.NAME:
                    this.nameCell = j;
                    break;
                case Constants.JOINING_DATE:
                    this.joiningDateCell = j;
                    break;
                case Constants.EMAIL_ADDRESS:
                    this.emailAddCell = j;
                    break;
                case Constants.DEPARTMENT:
                    this.deptCell = j;
                    break;
                case Constants.MONTHLY_SALARY:
                    this.monthlySalCell = j;
                    break;
                case Constants.JOB_STATUS:
                    this.jobStatCell = j;
                    break;
            }
        }
    }

    public <T> void writeIntoExcelFile(String fileLocation, List<T> employeeList) {
        log.info("file location : " + fileLocation);
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            log.info("creating sheet");
            XSSFSheet spreadsheet = workbook.createSheet(" Employee Data ");
            log.info("sheet created");
            XSSFRow row;
            int i = 0;
            for (T e : employeeList) {
                if (i == 0) {
                    row = spreadsheet.createRow(i);
                    log.info("setting header");
                    createHeaders(row);
                    log.info("header set: " + row.getCell(0) + "  " + row.getCell(1));
                    i++;
                }
                row = spreadsheet.createRow(i);
                log.info("adding employee to sheet");
                addEmployeeToSheet(e, row);
                log.info("row set: " + row.getCell(0) + "  " + row.getCell(1));
                i++;
            }
            FileOutputStream out = new FileOutputStream(new File(fileLocation));
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            log.info("file not found at location : " + fileLocation);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private <T> void addEmployeeToSheet(T genericEmployee, XSSFRow row) {
        if (genericEmployee instanceof SalesEmployeeCopy) {
            row.createCell(0).setCellValue(((SalesEmployeeCopy) genericEmployee).getName());
            row.createCell(1).setCellValue(((SalesEmployeeCopy) genericEmployee).getJoining_date());
            row.createCell(2).setCellValue(((SalesEmployeeCopy) genericEmployee).getEmail_addr());
            row.createCell(3).setCellValue(((SalesEmployeeCopy) genericEmployee).getDept());
            row.createCell(4).setCellValue(((SalesEmployeeCopy) genericEmployee).getMonthly_salary());
            row.createCell(5).setCellValue(((SalesEmployeeCopy) genericEmployee).getJob_status());
        } else {
            row.createCell(0).setCellValue(((Employee) genericEmployee).getName());
            row.createCell(1).setCellValue(((Employee) genericEmployee).getJoining_date());
            row.createCell(2).setCellValue(((Employee) genericEmployee).getEmail_addr());
            row.createCell(3).setCellValue(((Employee) genericEmployee).getDepartment());
            row.createCell(4).setCellValue(((Employee) genericEmployee).getMonthly_salary());
            row.createCell(5).setCellValue(((Employee) genericEmployee).getJob_status());
        }
    }

    private void createHeaders(XSSFRow row) {
        row.createCell(0).setCellValue(Constants.NAME);
        row.createCell(1).setCellValue(Constants.JOINING_DATE);
        row.createCell(2).setCellValue(Constants.EMAIL_ADDRESS);
        row.createCell(3).setCellValue(Constants.DEPARTMENT);
        row.createCell(4).setCellValue(Constants.MONTHLY_SALARY);
        row.createCell(5).setCellValue(Constants.JOB_STATUS);
    }
}
