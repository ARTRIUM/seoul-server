package com.artrium.demo.openapi;

import com.artrium.demo.common.CommonUtils;

import java.time.LocalDate;

/**
 * 문화행사 Open API
 * https://data.seoul.go.kr/dataList/OA-15486/S/1/datasetView.do
 */
public class CultureAPI {

    private static final String domain = "http://openapi.seoul.go.kr:8088/";
    private static final String serviceKey = "424a4f6e53646f7936396b7a544a41";
    private static final String culturePath = "/json/culturalEventInfo/";

    /**
     * 호출예시: http://openapi.seoul.go.kr:8088/{serviceKey}/{type}/{service}/{startOffset}/{endOffset}
     */
    private String getUrl(String start, String end) {
        StringBuilder builder = new StringBuilder();
        builder.append(domain).append(serviceKey)
                .append(culturePath)
                .append(start).append("/")
                .append(end).append("/");

        return builder.toString();
    }

    public String getCulture(String start, String end) {
        String url = getUrl(start, end);

        // call(url)    // TODO json 응답 파싱

        return null;
    }
}
