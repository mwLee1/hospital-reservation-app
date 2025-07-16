package com.codelab.hospital.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 로그인 성공 시 클라이언트에 전달되는 JWT 토큰 응답 DTO입니다.
 */
@Getter
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
}
