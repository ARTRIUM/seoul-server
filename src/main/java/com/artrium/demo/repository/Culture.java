package com.artrium.demo.repository;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Culture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "culture_id", updatable = false)
    private Long id;

    private String category;

    @Column(name = "gu_name", updatable = false)
    private String guName;

    private String title;

    @Column(name = "start_date", updatable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", updatable = false)
    private LocalDateTime endDate;

    private String place;

    private String org_name; // 기관명

    private String target; // 이용대상

    private String fee; // 가격

    private String homepage; // 홈페이지 링크

    private String img; // 대표 이미지

    private Double lot; // 위도

    private Double lat; // 경도

    private Long feedback; // 좋아요

    @Column(name = "is_free", updatable = false)
    private Boolean isFree; // 무료여부

    @OneToMany
    private List<Review> reviews;
}
