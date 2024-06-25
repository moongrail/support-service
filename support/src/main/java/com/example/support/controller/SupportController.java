package com.example.support.controller;

import com.example.support.dto.SupportPhraseRequest;
import com.example.support.dto.SupportPhraseResponse;
import com.example.support.service.SupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/support")
public class SupportController {
    private final SupportService supportService;

    @GetMapping
    public SupportPhraseResponse getSupportPhrase(){
        return supportService.getRandomPhrase();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addPhrase(@RequestBody SupportPhraseRequest phraseRequest){
        supportService.addPhrase(phraseRequest);
    }
}
