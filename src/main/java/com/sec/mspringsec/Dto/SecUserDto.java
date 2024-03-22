package com.sec.mspringsec.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SecUserDto {
    private Integer id;
    private String email;
    private String pwd;
    private String role;
    private String fullName;
}
