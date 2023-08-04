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
public class GetAllBukuByStatusRequest implements Serializable {
    private static final long serialVersionUID = 3967577865119396452L;

    private Integer pageNo;
    private Integer pageSize;
    private String status;
}
