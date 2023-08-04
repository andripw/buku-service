package com.test.pendaftaranbuku.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusBukuRequest implements Serializable {
    private static final long serialVersionUID = -6797894241448328255L;

    private String idBuku;
    private String peminjam;
    private String tanggalPinjam;
    private String tanggalKembali;
}
