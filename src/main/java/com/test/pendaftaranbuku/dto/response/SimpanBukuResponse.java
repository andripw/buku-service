package com.test.pendaftaranbuku.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpanBukuResponse implements Serializable {
    private static final long serialVersionUID = -4405606309876933183L;

    private boolean isSuccess;
}
