package com.test.pendaftaranbuku.repository;

import com.test.pendaftaranbuku.entity.Buku;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BukuRepository extends JpaRepository<Buku, String> {

    Page<Buku> findAllByStatusBuku(String status, Pageable pageable);
}
