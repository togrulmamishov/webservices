package com.azercell.webservices.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class HelloWorldController {

    private final MessageSource messageSource;

//    @GetMapping(path = "/hello-world")
//    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
//        return messageSource.getMessage("good.morning.message",null, locale);
//    }

    @GetMapping(path = "/hello-world")
    public String helloWorldInternationalized() {
        return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    }
}
