package com.artrium.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CultureInfo {

    private String title; // 공연/행사명
    private String guName; // 구 이름
    private String image; // 대표 이미지
    private String startDate; // 행사 시작 일자
    private String endDate; // 행사 마감 일자
    private String category; // 분류
}
