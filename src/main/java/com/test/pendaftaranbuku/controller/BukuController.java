package com.test.pendaftaranbuku.controller;

import com.test.pendaftaranbuku.dto.request.*;
import com.test.pendaftaranbuku.dto.response.GetAllBukuResponse;
import com.test.pendaftaranbuku.dto.response.SimpanBukuResponse;
import com.test.pendaftaranbuku.service.BukuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequiredArgsConstructor
public class BukuController {

    private final BukuService bukuService;

    @ApiOperation("Simpan Buku")
    @PostMapping("/simpan")
    public SimpanBukuResponse simpanBuku(@RequestBody SimpanBukuRequest request) {
        return bukuService.simpanBuku(request);
    }

    @ApiOperation("Get All Buku")
    @PostMapping("/get/all")
    public GetAllBukuResponse getAllBuku(@RequestBody GetAllBukuRequest request) {
        return bukuService.getAllBuku(request);
    }

    @ApiOperation("Get All Buku By Status")
    @PostMapping("/get/all/by-status")
    public GetAllBukuResponse getAllBukuByStatus(@RequestBody GetAllBukuByStatusRequest request) {
        return bukuService.getAllBukuByStatus(request);
    }

    @ApiOperation("Update Data Buku")
    @PostMapping("/update-data")
    public SimpanBukuResponse updateDataBuku(@RequestBody UpdateDataBukuRequest request) {
        return bukuService.updateDataBuku(request);
    }

    @ApiOperation("Update Status Buku")
    @PostMapping("/update-status")
    public SimpanBukuResponse updateStatusBuku(@RequestBody UpdateStatusBukuRequest request) {
        return bukuService.updateStatusBuku(request);
    }

    @ApiOperation("Delete Buku By Id Buku")
    @PostMapping("/delete/by-id")
    public SimpanBukuResponse deleteBukuById(@RequestBody DeleteBukuRequest request) {
        return bukuService.deleteBukuByIdBuku(request);
    }
}
