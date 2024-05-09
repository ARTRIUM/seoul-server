package com.artrium.demo.service;

import com.artrium.demo.dto.CultureInfo;
import com.artrium.demo.repository.Culture;
import com.artrium.demo.repository.CultureRepository;
import com.artrium.demo.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CultureService {

    private final CultureRepository cultureRepository;

    public List<CultureInfo> getCultureInfos(String guName, Pageable pageable) {
        return cultureRepository.findByGuName(guName, pageable).stream()
                .map(culture -> new CultureInfo(
                        culture.getId(),
                        culture.getTitle(),
                        culture.getGuName(),
                        culture.getImg(),
                        culture.getStartDate().format(DateTimeFormatter.ofPattern(Utils.FORMATTER_KOR_YYYYMMDD)),
                        culture.getEndDate().format(DateTimeFormatter.ofPattern(Utils.FORMATTER_KOR_YYYYMMDD)),
                        culture.getCategory()))
                .toList();
    }

    public Culture getCultureDetail(Long cultureId) {
        Optional<Culture> culture = cultureRepository.findById(cultureId);
        return culture.get();
    }
}
