package com.scm.helpers;

import com.scm.entities.Contact;
import com.scm.services.ContactService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Service
public class ExcelExportHandler {

    private XSSFWorkbook workbook;

    private XSSFSheet sheet;

    @Autowired
    private ContactService contactService;

    public ExcelExportHandler(){
        workbook = new XSSFWorkbook();
    }

    public void createCells(Row row, int columnCount, Object data, CellStyle style){
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if(data !=null){
            if(data instanceof Integer){
                cell.setCellValue((Integer) data);
            }if(data instanceof Double){
                cell.setCellValue((Double) data);
            }if(data instanceof String){
                cell.setCellValue((String) data);
            }if(data instanceof Long){
                cell.setCellValue((Long) data);
            }if(data instanceof Boolean){
                cell.setCellValue((Boolean) data);
            }if(data instanceof Date) {
                cell.setCellValue((Date) data);
            }
        }
        cell.setCellStyle(style);
    }

    private void createHeaderRow(){
        sheet   = workbook.createSheet("Customer Information");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(20);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        createCell(row, 0, "Contact Information", style);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
        font.setFontHeightInPoints((short) 10);

        row = sheet.createRow(1);
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "ID", style);
        createCell(row, 1, "Contact Name", style);
        createCell(row, 2, "Email", style);
        createCell(row, 3, "Address", style);
        createCell(row, 4, "Phone", style);
    }

    private void writeContactData(){

        List<Contact> contactList = contactService.getAll();

        int rowCount = 2;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Contact contact : contactList){
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, contact.getId(), style);
            createCell(row, columnCount++, contact.getName(), style);
            createCell(row, columnCount++, contact.getEmail(), style);
            createCell(row, columnCount++, contact.getAddress(), style);
            createCell(row, columnCount++, contact.getPhoneNumber(), style);
        }

    }

    public void exportDataToExcel(HttpServletResponse response) throws IOException {
        createHeaderRow();
        writeContactData();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

}
