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
public class GetAllBukuRequest implements Serializable {
    private static final long serialVersionUID = -8932185818998940632L;

    private Integer pageNo;
    private Integer pageSize;
}
