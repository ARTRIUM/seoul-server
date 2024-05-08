package com.artrium.demo.service;

import com.artrium.demo.dto.CultureDetailDTO;
import com.artrium.demo.repository.Culture;
import com.artrium.demo.repository.CultureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CultureService {

    private final CultureRepository cultureRepository;

    public Culture getCultureDetail(Long cultureId) {
        Optional<Culture> culture = cultureRepository.findById(cultureId);
        return culture.get();
    }
}