package com.wdc.test.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

@RestController
@RequestMapping("test")
public class TestController {

    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (null == text || text.length() == 0) {
                    setValue(null);
                } else {
                    try {
                        setValue(LocalDate.parse(text));
                    } catch (Exception ex) {
                        throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
                    }
                }
            }
        });
    }

    @GetMapping("date")
    String getDateStr(@RequestParam LocalDate date) {
        return date.toString();
    }
}
