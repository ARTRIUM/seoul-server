package com.artrium.demo.controller;

import com.artrium.demo.dto.CultureDetailDTO;
import com.artrium.demo.repository.Culture;
import com.artrium.demo.service.CultureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CultureController {

    private final CultureService cultureService;

    @GetMapping("/cultures/{cultureId}")
    public String getCultureDetail(@PathVariable("cultureId") Long cultureId, Model model) {
        CultureDetailDTO culture = cultureService.getCultureDetail(cultureId);
        model.addAttribute("culture", culture);
        return "culture/detailPage";
    }
}
