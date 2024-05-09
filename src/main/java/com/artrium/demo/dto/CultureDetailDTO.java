package com.artrium.demo.dto;

import com.artrium.demo.repository.Culture;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter @Setter
@NoArgsConstructor
public class CultureDetailDTO {
    private Long culture_no; // 문화 인덱스
    private String category; // 분류
    private String guName; // 구 이름
    private String title; // 공연/행사명
    private String place; // 장소
    private String orgName; // 기관명
    private String target; // 이용대상
    private String fee; // 가격
    private String homepage; // 홈페이지 링크
    private String image; // 대표 이미지
    private Double lot; // 위도
    private Double lat; // 경도
    private Long feedback; // 좋아요
    private boolean isFree; // 무료여부

    public CultureDetailDTO(Culture culture){
        this.culture_no = culture.getId();
        this.category = culture.getCategory();
        this.guName = culture.getGuName();
        this.title = culture.getTitle();
        this.place = culture.getPlace();
        this.orgName = culture.getOrgName();
        this.target = culture.getTarget();
        this.fee = culture.getFee();
        this.homepage = culture.getHomepage();
        this.image = culture.getImg();
        this.lot = culture.getLot();
        this.lat = culture.getLat();
        this.feedback = culture.getFeedback();
        this.isFree = culture.getIsFree();
    }
}
