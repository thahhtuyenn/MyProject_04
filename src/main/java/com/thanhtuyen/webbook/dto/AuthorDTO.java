package com.thanhtuyen.webbook.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthorDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private boolean is_active;
}
