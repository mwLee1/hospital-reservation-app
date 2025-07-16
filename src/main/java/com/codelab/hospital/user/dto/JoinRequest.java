package com.codelab.hospital.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * 회원가입 요청을 처리하기 위한 DTO입니다.
 */
@Getter
@Setter
public class JoinRequest {
    private String email;     // 로그인 ID
    private String password;  // 비밀번호
    private String name;      // 사용자 이름
    private String phone;     // 전화번호
    private LocalDate birth;  // 생년월일
    private boolean isAdmin;  // true면 관리자 회원가입
}
