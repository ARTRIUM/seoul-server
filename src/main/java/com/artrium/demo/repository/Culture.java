package com.artrium.demo.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "culture")
public class Culture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "culture_id", updatable = false)
    private Long id;

    @Column(columnDefinition = "LONGTEXT")
    private String category;

    @Column(name = "gu_name", updatable = false, columnDefinition="LONGTEXT")
    private String guName;

    @Column(columnDefinition = "LONGTEXT")
    private String title;

    @Column(name = "start_date", updatable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", updatable = false)
    private LocalDateTime endDate;

    @Column(columnDefinition = "LONGTEXT")
    private String place;

    @Column(columnDefinition = "LONGTEXT")
    private String orgName; // 기관명

    @Column(columnDefinition = "LONGTEXT")
    private String target; // 이용대상

    @Column(columnDefinition = "LONGTEXT")
    private String fee; // 가격

    @Column(columnDefinition = "LONGTEXT")
    private String homepage; // 홈페이지 링크

    @Column(columnDefinition = "LONGTEXT")
    private String img; // 대표 이미지

    private Double lot; // 위도

    private Double lat; // 경도

    private Long feedback; // 좋아요

    @Column(name = "is_free", updatable = false)
    private Boolean isFree; // 무료여부

    @OneToMany
    private List<Review> reviews;
}
