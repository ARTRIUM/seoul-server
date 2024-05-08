package com.artrium.demo.controller;

import com.artrium.demo.dto.CultureDetailDTO;
import com.artrium.demo.repository.Culture;
import com.artrium.demo.service.CultureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CultureController {

    private final CultureService cultureService;


    @GetMapping("/cultures/{cultureId}")
    public Culture getCultureDetail(@PathVariable("cultureId") Long cultureId) {
        return cultureService.getCultureDetail(cultureId);
    }
}
