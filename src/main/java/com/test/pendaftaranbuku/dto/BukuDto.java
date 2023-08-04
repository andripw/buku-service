package com.test.pendaftaranbuku.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BukuDto implements Serializable {
    private static final long serialVersionUID = 2336446789065616206L;

    private String id;
    private String judul;
    private String pengarang;
    private String penerbit;

    @JsonFormat(pattern="dd MMMM yyyy HH:mm")
    private Date tanggalTerbit;

    private String tebalBuku;
    private String statusBuku;
    private String peminjam;

    @JsonFormat(pattern="dd MMMM yyyy HH:mm")
    private String tanggalPinjam;

    @JsonFormat(pattern="dd MMMM yyyy HH:mm")
    private String tanggalKembali;
}
