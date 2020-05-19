package me.lexie.finrainterview.demo.controller;

import me.lexie.finrainterview.demo.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class PhoneController {

    @Autowired
    private PhoneNumberService service;

    @RequestMapping("/phonenumbers/{phoneNumber}")
    private ResponseEntity<List<String>> getPhoneNumbers(@PathVariable String phoneNumber, @RequestParam int currentPage, @RequestParam int itemsCount) {
        List<String> phoneNumbers = service.generatePhoneNumbers(phoneNumber);
        List<String> result = null;
        // Pagination
        int start = currentPage * itemsCount;
        int end = (currentPage+1) * itemsCount;
        if (start < 0 || end < 0) {
            result = new ArrayList<>();
        } else {
            result = phoneNumbers.subList(start,end);
            Collections.sort(result);
        }
        return new ResponseEntity<List<String>>(result,HttpStatus.OK);

    }

}
