package com.artrium.demo.service;

import com.artrium.demo.dto.CultureDetailDTO;
import com.artrium.demo.dto.CultureInfo;
import com.artrium.demo.repository.Culture;
import com.artrium.demo.repository.CultureRepository;
import com.artrium.demo.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CultureService {

    private final CultureRepository cultureRepository;

    public Page<CultureInfo> getCultureInfos(String guName, Pageable pageable) {
        validatePage(pageable);

        return cultureRepository.findByGuName(guName, pageable)
            .map(culture ->
                new CultureInfo(
                        culture.getId(),
                        culture.getTitle(),
                        culture.getGuName(),
                        culture.getImg(),
                        culture.getStartDate().format(DateTimeFormatter.ofPattern(Utils.FORMATTER_KOR_YYYYMMDD)),
                        culture.getEndDate().format(DateTimeFormatter.ofPattern(Utils.FORMATTER_KOR_YYYYMMDD)),
                        culture.getCategory()
                )
            );
    }

    private void validatePage(Pageable pageable) {
        if (pageable.getPageNumber() < 0 || pageable.getPageNumber() > 9 || pageable.getPageSize() != 15) {
            throw new IllegalArgumentException("잘못된 페이지값 입력");
        }
    }

    public CultureDetailDTO getCultureDetail(Long cultureId) {
        Optional<Culture> culture = cultureRepository.findById(cultureId);
        return new CultureDetailDTO(culture.get());
    }
}
