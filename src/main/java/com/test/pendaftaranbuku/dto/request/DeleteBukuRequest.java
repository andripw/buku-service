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
public class DeleteBukuRequest implements Serializable {
    private static final long serialVersionUID = 8517917119844069473L;

    private String idBuku;
}
