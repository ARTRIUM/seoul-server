package com.artrium.demo.controller;

import com.artrium.demo.dto.CultureInfo;
import com.artrium.demo.service.CultureService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
}
