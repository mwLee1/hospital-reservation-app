package com.codelab.hospital.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 시스템 내 사용자(환자/관리자)를 나타내는 JPA 엔티티 클래스입니다.
 * - 일반 유저와 병원 관리자를 구분하기 위해 role, type 필드 사용
 * - 관리자는 승인 상태(isApproved)에 따라 로그인/기능 제한 가능
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "users") // MySQL 예약어 user와 충돌 방지
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;  // 로그인 ID로 사용됨

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String phone;  // 전화번호

    @Column(nullable = false)
    private LocalDate birth;  // 생년월일

    @Column(nullable = false)
    private String role;  // ROLE_USER / ROLE_ADMIN

    @Column(nullable = false)
    private String type;  // 일반 / 관리자

    @Column(nullable = false)
    private boolean isApproved = true;

    public User(String email, String password, String name, String phone, LocalDate birth, String role, String type, boolean isApproved) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.role = role;
        this.type = type;
        this.isApproved = isApproved;
    }

    public static User createUser(String email, String password, String name, String phone, LocalDate birth) {
        return new User(email, password, name, phone, birth, "ROLE_USER", "일반", true);
    }

    public static User createAdmin(String email, String password, String name, String phone, LocalDate birth) {
        return new User(email, password, name, phone, birth, "ROLE_ADMIN", "관리자", false);
    }
}
