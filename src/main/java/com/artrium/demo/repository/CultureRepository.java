package com.artrium.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CultureRepository extends JpaRepository<Culture, Long> {

    public Optional<Culture> findById(Long id);

    Page<Culture> findByGuName(String guName, Pageable pageable);

}
