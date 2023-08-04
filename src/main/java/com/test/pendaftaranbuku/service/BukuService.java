package com.test.pendaftaranbuku.service;

import com.test.pendaftaranbuku.constant.Constant;
import com.test.pendaftaranbuku.dto.BukuDto;
import com.test.pendaftaranbuku.dto.request.*;
import com.test.pendaftaranbuku.dto.response.GetAllBukuResponse;
import com.test.pendaftaranbuku.dto.response.SimpanBukuResponse;
import com.test.pendaftaranbuku.entity.Buku;
import com.test.pendaftaranbuku.exception.BadRequestException;
import com.test.pendaftaranbuku.exception.DataNotFoundException;
import com.test.pendaftaranbuku.repository.BukuRepository;
import com.test.pendaftaranbuku.utils.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BukuService {

    private final BukuRepository bukuRepository;

    @Transactional
    public SimpanBukuResponse simpanBuku(SimpanBukuRequest request) {
        log.info("simpan buku req : {}", request);

        boolean isSuccess = false;

        if (ObjectUtils.isEmpty(request.getJudul()))
            throw new BadRequestException("judul buku tidak boleh kosong");

        if (ObjectUtils.isEmpty(request.getPengarang()))
            throw new BadRequestException("pengarang tidak boleh kosong");

        if (ObjectUtils.isEmpty(request.getPenerbit()))
            throw new BadRequestException("penerbit tidak boleh kosong");

        if (ObjectUtils.isEmpty(request.getTanggalTerbit()))
            throw new BadRequestException("tanggalTerbit tidak boleh kosong");

        if (ObjectUtils.isEmpty(request.getTebalBuku()))
            throw new BadRequestException("tebal buku tidak boleh kosong");

        try {
            var buku = bukuRepository.save(Buku.builder()
                    .id(null)
                    .judul(request.getJudul())
                    .pengarang(request.getPengarang())
                    .penerbit(request.getPenerbit())
                    .tanggalTerbit(CommonUtil.getDateByStringFormat1(request.getTanggalTerbit()))
                    .tebalBuku(request.getTebalBuku())
                    .statusBuku("0")
                    .peminjam("-")
                    .tanggalPinjam(null)
                    .tanggalKembali(null)
                    .tanggalBuat(Constant.DATE_NOW)
                    .tanggalUbah(null)
                    .build());
            log.info("buku : {}", buku);
            if (!ObjectUtils.isEmpty(buku))
                isSuccess = true;

            return SimpanBukuResponse.builder()
                    .isSuccess(isSuccess)
                    .build();
        } catch (BadRequestException e) {
            log.error("error e : {}", e.getMessage());
            throw e;
        } catch (ParseException e) {
            log.error("error e : {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public GetAllBukuResponse getAllBuku(GetAllBukuRequest request) {
        log.info("get all buku req : {}", request);

        // validation input
        if (ObjectUtils.isEmpty(request.getPageNo()) || request.getPageNo() <= 0)
            throw new BadRequestException("pageNo tidak valid");
        if (ObjectUtils.isEmpty(request.getPageSize()) || request.getPageSize() <= 0)
            throw new BadRequestException("pageSize tidak valid");

        try {
            // set paging
            var pageable = PageRequest.of(request.getPageNo()-1, request.getPageSize(), Sort.by("id").ascending());
            // get all buku by paging
            var page = bukuRepository.findAll(pageable);
            log.info(" buku list : {}", page.toList());
            if (CollectionUtils.isEmpty(page.toList())) {
                log.info(Constant.DATA_TIDAK_DITEMUKAN);
                throw new DataNotFoundException(Constant.DATA_TIDAK_DITEMUKAN);
            }

            // mapping list blog
            List<BukuDto> bukuDtoList = new ArrayList<>();
            for (Buku buku : page.toList()) {
                bukuDtoList.add(BukuDto.builder()
                        .id(buku.getId())
                        .judul(buku.getJudul())
                        .pengarang(buku.getPengarang())
                        .penerbit(buku.getPenerbit())
                        .tanggalTerbit(buku.getTanggalTerbit())
                        .tebalBuku(buku.getTebalBuku())
                        .statusBuku(buku.getStatusBuku())
                        .peminjam(buku.getPeminjam())
                        .tanggalPinjam(CommonUtil.getDateFormat(buku.getTanggalPinjam()))
                        .tanggalKembali(CommonUtil.getDateFormat(buku.getTanggalKembali()))
                        .build());
            }
            log.info("buku dto list : {}", bukuDtoList);

            return GetAllBukuResponse.builder()
                    .bukuDtoList(bukuDtoList)
                    .totalPage(page.getTotalPages())
                    .totalElements(page.getTotalElements())
                    .build();
        } catch (DataNotFoundException e) {
            log.error("error e : {}", e.getMessage());
            throw e;
        }
    }

    public GetAllBukuResponse getAllBukuByStatus(GetAllBukuByStatusRequest request) {
        log.info("get all buku by status req : {}", request);

        // validation input
        if (ObjectUtils.isEmpty(request.getPageNo()) || request.getPageNo() <= 0)
            throw new BadRequestException("pageNo tidak valid");
        if (ObjectUtils.isEmpty(request.getPageSize()) || request.getPageSize() <= 0)
            throw new BadRequestException("pageSize tidak valid");
        if (ObjectUtils.isEmpty(request.getStatus()))
            throw new BadRequestException("status tidak valid");

        try {
            // set paging
            var pageable = PageRequest.of(request.getPageNo()-1, request.getPageSize(), Sort.by("id").ascending());
            // get all buku by paging
            var page = bukuRepository.findAllByStatusBuku(request.getStatus(), pageable);
            log.info(" buku list by status : {}", page.toList());
            if (CollectionUtils.isEmpty(page.toList())) {
                log.info(Constant.DATA_TIDAK_DITEMUKAN);
                throw new DataNotFoundException(Constant.DATA_TIDAK_DITEMUKAN);
            }

            // mapping list blog
            List<BukuDto> bukuDtoList = new ArrayList<>();
            for (Buku buku : page.toList()) {
                bukuDtoList.add(BukuDto.builder()
                        .id(buku.getId())
                        .judul(buku.getJudul())
                        .pengarang(buku.getPengarang())
                        .penerbit(buku.getPenerbit())
                        .tanggalTerbit(buku.getTanggalTerbit())
                        .tebalBuku(buku.getTebalBuku())
                        .statusBuku(buku.getStatusBuku())
                        .peminjam(buku.getPeminjam())
                        .tanggalPinjam(CommonUtil.getDateFormat(buku.getTanggalPinjam()))
                        .tanggalKembali(CommonUtil.getDateFormat(buku.getTanggalKembali()))
                        .build());
            }
            log.info("buku dto list : {}", bukuDtoList);

            return GetAllBukuResponse.builder()
                    .bukuDtoList(bukuDtoList)
                    .totalPage(page.getTotalPages())
                    .totalElements(page.getTotalElements())
                    .build();
        } catch (DataNotFoundException e) {
            log.error("error e : {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public SimpanBukuResponse updateDataBuku(UpdateDataBukuRequest request) {
        log.info("update data buku req : {}", request);

        // validation input
        if (ObjectUtils.isEmpty(request.getIdBuku()))
            throw new BadRequestException("id tidak valid");

        if (ObjectUtils.isEmpty(request.getJudul()))
            throw new BadRequestException("judul buku tidak boleh kosong");

        if (ObjectUtils.isEmpty(request.getPengarang()))
            throw new BadRequestException("pengarang tidak boleh kosong");

        if (ObjectUtils.isEmpty(request.getPenerbit()))
            throw new BadRequestException("penerbit tidak boleh kosong");

        if (ObjectUtils.isEmpty(request.getTanggalTerbit()))
            throw new BadRequestException("tanggalTerbit tidak boleh kosong");

        if (ObjectUtils.isEmpty(request.getTebalBuku()))
            throw new BadRequestException("tebal buku tidak boleh kosong");

        try {
            var buku = getBukuById(request.getIdBuku());
            log.info("buku : {}", buku);
            if (ObjectUtils.isEmpty(buku))
                throw new DataNotFoundException("ID Buku " + request.getIdBuku() + " tidak ditemukan");

            bukuRepository.save(Buku.builder()
                    .id(buku.getId())
                    .judul(request.getJudul())
                    .pengarang(request.getPengarang())
                    .penerbit(request.getPenerbit())
                    .tanggalTerbit(CommonUtil.getDateByStringFormat1(request.getTanggalTerbit()))
                    .tebalBuku(request.getTebalBuku())
                    .statusBuku(buku.getStatusBuku())
                    .peminjam(buku.getPeminjam())
                    .tanggalBuat(buku.getTanggalBuat())
                    .tanggalUbah(Constant.DATE_NOW)
                    .build());

            return SimpanBukuResponse.builder()
                    .isSuccess(true)
                    .build();
        } catch (BadRequestException e) {
            log.error("error e : {}", e.getMessage());
            throw e;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public SimpanBukuResponse updateStatusBuku(UpdateStatusBukuRequest request) {
        log.info("update status buku req : {}", request);

        // validation input
        if (ObjectUtils.isEmpty(request.getIdBuku()))
            throw new BadRequestException("id tidak valid");

        if (ObjectUtils.isEmpty(request.getPeminjam()))
            throw new BadRequestException("peminjam tidak boleh kosong");

        if (ObjectUtils.isEmpty(request.getTanggalPinjam()))
            throw new BadRequestException("tanggalPinjam tidak boleh kosong");

        if (ObjectUtils.isEmpty(request.getTanggalKembali()))
            throw new BadRequestException("tanggalKembali tidak boleh kosong");

        try {
            var buku = getBukuById(request.getIdBuku());
            log.info("buku : {}", buku);
            if (ObjectUtils.isEmpty(buku))
                throw new DataNotFoundException("ID Buku " + request.getIdBuku() + " tidak ditemukan");

            bukuRepository.save(Buku.builder()
                    .id(buku.getId())
                    .judul(buku.getJudul())
                    .pengarang(buku.getPengarang())
                    .penerbit(buku.getPenerbit())
                    .tanggalTerbit(buku.getTanggalTerbit())
                    .tebalBuku(buku.getTebalBuku())
                    .statusBuku("1")
                    .peminjam(request.getPeminjam())
                    .tanggalPinjam(CommonUtil.getDateByStringFormat2(request.getTanggalPinjam()))
                    .tanggalKembali(CommonUtil.getDateByStringFormat2(request.getTanggalKembali()))
                    .tanggalBuat(buku.getTanggalBuat())
                    .tanggalUbah(Constant.DATE_NOW)
                    .build());

            return SimpanBukuResponse.builder()
                    .isSuccess(true)
                    .build();
        } catch (BadRequestException e) {
            log.error("error e : {}", e.getMessage());
            throw e;
        } catch (ParseException e) {
            log.error("error e : {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public SimpanBukuResponse deleteBukuByIdBuku(DeleteBukuRequest request) {
        log.info("delete buku req : {}", request);

        if (ObjectUtils.isEmpty(request.getIdBuku()))
            throw new BadRequestException("idBuku tidak boleh kosong");

        try {
            var buku = getBukuById(request.getIdBuku());
            log.info("buku : {}", buku);
            if (ObjectUtils.isEmpty(buku))
                throw new DataNotFoundException("ID Buku " + request.getIdBuku() + " tidak ditemukan");

            bukuRepository.deleteById(buku.getId());
            log.info("successfully delete buku by id buku");

            return SimpanBukuResponse.builder()
                    .isSuccess(true)
                    .build();
        } catch (BadRequestException | DataNotFoundException e) {
            log.error("error e : {}", e.getMessage());
            throw e;
        }
    }

    private Buku getBukuById(String id) {
        return bukuRepository.findById(id).orElse(null);
    }
}
