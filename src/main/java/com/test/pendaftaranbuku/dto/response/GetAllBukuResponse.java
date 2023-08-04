package com.test.pendaftaranbuku.dto.response;

import com.test.pendaftaranbuku.dto.BukuDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllBukuResponse implements Serializable {
    private static final long serialVersionUID = 7804720327263025333L;

    private List<BukuDto> bukuDtoList;
    private Integer totalPage;
    private Long totalElements;
}
