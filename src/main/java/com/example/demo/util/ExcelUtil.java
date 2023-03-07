package com.example.demo.util;

import com.example.demo.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
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
                    assignCellValues(row, nameCell, joiningDateCell, emailAddCell, deptCell, monthlySalCell, jobStatCell);
                    i++;
                    continue;
                }
                addEmployeeToEmpList(employeeList, nameCell, joiningDateCell, emailAddCell, deptCell, monthlySalCell, jobStatCell, row);
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

    private void addEmployeeToEmpList(List<Employee> employeeList, int nameCell, int joiningDateCell, int emailAddCell, int deptCell, int monthlySalCell, int jobStatCell, Row row) {
        employeeList.add(new Employee()
                .name(row.getCell(nameCell).toString())
                .joining_date(row.getCell(joiningDateCell).toString())
                .email_addr(row.getCell(emailAddCell).toString())
                .monthly_salary(row.getCell(monthlySalCell).toString())
                .dept(row.getCell(deptCell).toString())
                .job_status(row.getCell(jobStatCell).toString()));
    }

    private void assignCellValues(Row row, int nameCell, int joiningDateCell, int emailAddCell, int deptCell, int monthlySalCell, int jobStatCell) {
        for (int j = 0; j < row.getLastCellNum(); j++) {
            log.info("cell header value: " + row.getCell(j));
            switch (row.getCell(j).toString()) {
                case Constants.NAME:
                    nameCell = j;
                    break;
                case Constants.JOINING_DATE:
                    joiningDateCell = j;
                    break;
                case Constants.EMAIL_ADDRESS:
                    emailAddCell = j;
                    break;
                case Constants.DEPARTMENT:
                    deptCell = j;
                    break;
                case Constants.MONTHLY_SALARY:
                    monthlySalCell = j;
                    break;
                case Constants.JOB_STATUS:
                    jobStatCell = j;
                    break;
            }
        }
    }

    public void writeIntoExcelFile(String fileLocation, List<Employee> employeeList) {
        log.info("file location : " + fileLocation);
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            log.info("creating sheet");
            XSSFSheet spreadsheet = workbook.createSheet(" Employee Data ");
            log.info("sheet created");
            XSSFRow row;
            int i = 0;
            for (Employee e : employeeList) {
                row = spreadsheet.createRow(i);
                if (i == 0) {
                    log.info("setting header");
                    createHeaders(row);
                    log.info("header set: " + row.getCell(0) + "  " + row.getCell(1));
                    i++;
                    continue;
                }
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

    private void addEmployeeToSheet(Employee employee, XSSFRow row) {
        row.createCell(0).setCellValue(employee.getName());
        row.createCell(1).setCellValue(employee.getJoining_date());
        row.createCell(2).setCellValue(employee.getEmail_addr());
        row.createCell(3).setCellValue(employee.getDept());
        row.createCell(4).setCellValue(employee.getMonthly_salary());
        row.createCell(5).setCellValue(employee.getJob_status());
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
