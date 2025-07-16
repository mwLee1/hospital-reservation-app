package com.codelab.hospital.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 로그인 요청을 처리하기 위한 DTO입니다.
 */
@Getter
@Setter
public class LoginRequest {
    private String username;
    private String password;
}
