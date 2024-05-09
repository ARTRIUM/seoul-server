package com.artrium.demo.controller;

import com.artrium.demo.dto.CultureInfo;
import com.artrium.demo.repository.Culture;
import com.artrium.demo.service.CultureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebController {

    private final CultureService cultureService;

    @RequestMapping("/")
    public String home(Pageable pageable, Model model) {
        final List<CultureInfo> cultureInfos = cultureService.getCultureInfos("", pageable);

        model.addAttribute("cultureInfos", cultureInfos);

        return "index";
    }

    @RequestMapping("/cultures/{id}")
    public String getCultureDetail(@PathVariable("id") Long cultureId, Pageable pageable, Model model) {
        log.error("test: " + cultureId);
        model.addAttribute("cultureInfo", cultureService.getCultureDetail(cultureId));
        return "detail";
    }
}
