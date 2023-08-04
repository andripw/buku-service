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
public class UpdateDataBukuRequest implements Serializable {
    private static final long serialVersionUID = 8194988528892192952L;

    private String idBuku;
    private String judul;
    private String pengarang;
    private String penerbit;
    private String tanggalTerbit;
    private String tebalBuku;
}
