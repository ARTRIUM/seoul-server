package com.artrium.demo.openapi;

import com.artrium.demo.common.ArtriumException;
import com.artrium.demo.common.CommonUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

public class WeatherAPI {

    private static final String domain = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?";
    private static final String serviceKey = "P67cLmcxkzL21GLQ9aYyoaxYg9d9OTbkc%2BcShD%2F6ce%2FiMzP4F2t7qA87%2Fa%2FtPCrzROxYqul87sS9Q0mO6kjPdg%3D%3D";
    private static final String dataType = "JSON";
    private static final String baseTime = "0500";

    private String call(String url) throws ArtriumException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new ArtriumException("weather API call fail");
        }
    }

    /**
     * 호출예시: https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey=P67cLmcxkzL21GLQ9aYyoaxYg9d9OTbkc%2BcShD%2F6ce%2FiMzP4F2t7qA87%2Fa%2FtPCrzROxYqul87sS9Q0mO6kjPdg%3D%3D&pageNo=1&numOfRows=1000&dataType=XML&base_date=20210628&base_time=0500&nx=55&ny=127
     */
    private String getUrl(String baseDate, String nx, String ny) {
        StringBuilder builder = new StringBuilder();
        builder.append(domain)
                .append("serviceKey=").append(serviceKey).append("&")
                .append("dataType=").append(dataType).append("&")
                .append("base_date=").append(baseDate).append("&")
                .append("base_time=").append(baseTime).append("&")
                .append("nx=").append(nx).append("&")
                .append("ny=").append(ny);

        return builder.toString();
    }

    public String getWeather(String nx, String ny) {
        LocalDate now = LocalDate.now();
        String baseDate = CommonUtils.getLocalDate(now);
        String url = getUrl(baseDate, nx, ny);

        // call(url)

        return null;
    }
}
