package com.scm.controllers;

import com.scm.helpers.ExcelExportHandler;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.entities.Contact;
import com.scm.services.ContactService;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ApiController {

    // get contact

    @Autowired
    private ContactService contactService;

    @Autowired
    private ExcelExportHandler excelExportHandler;

    @GetMapping("/contacts/{contactId}")
    public Contact getContact(@PathVariable String contactId) {
        return contactService.getById(contactId);
    }

    @GetMapping("/contacts/export-contact")
    public ResponseEntity exportContact(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Contact_Info.xlsx";
        response.setHeader(headerKey, headerValue);
        excelExportHandler.exportDataToExcel(response);

        return new ResponseEntity(HttpStatus.OK);
    }

}
