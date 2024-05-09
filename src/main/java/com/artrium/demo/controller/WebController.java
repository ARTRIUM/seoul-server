package com.artrium.demo.controller;

import com.artrium.demo.dto.CultureInfo;
import com.artrium.demo.repository.Culture;
import com.artrium.demo.service.CultureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebController {

    private final CultureService cultureService;

    @RequestMapping("/")
    public String home(
            @PageableDefault(page=0, size=15) Pageable pageable,
            @RequestParam(defaultValue="강남구") String guName,
            Model model
    ) {
        final Page<CultureInfo> cultureInfos = cultureService.getCultureInfos(guName, pageable);

        model.addAttribute("cultureInfos", cultureInfos);
        model.addAttribute("pageTotalNumber", Math.min(9, cultureInfos.getTotalPages()));
        model.addAttribute("pageCurrentNumber", cultureInfos.getNumber());

        return "index";
    }

    @RequestMapping("/cultures/{cultureId}")
    public String getCultureDetail(@PathVariable("cultureId") Long cultureId, Pageable pageable, Model model) {
        System.out.println("cultureId" + cultureId);
        model.addAttribute("culture", cultureService.getCultureDetail(cultureId));
        return "culture/detailPage";
    }
}
