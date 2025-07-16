package com.codelab.hospital.security;

import java.util.Map;

/**
 * OAuth2 플랫폼별 사용자 정보를 추상화하여 하나의 구조로 변환합니다.
 */
public class OAuthAttributes {

    private final String email;
    private final String name;

    public OAuthAttributes(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public static OAuthAttributes of(String provider, Map<String, Object> attributes) {
        switch (provider) {
            case "kakao":
                return ofKakao(attributes);
            case "naver":
                return ofNaver(attributes);
            case "google":
            default:
                return ofGoogle(attributes);
        }
    }

    private static OAuthAttributes ofGoogle(Map<String, Object> attributes) {
        return new OAuthAttributes(
                (String) attributes.get("email"),
                (String) attributes.get("name")
        );
    }

    private static OAuthAttributes ofKakao(Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        return new OAuthAttributes(
                (String) kakaoAccount.get("email"),
                (String) profile.get("nickname")
        );
    }

    private static OAuthAttributes ofNaver(Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return new OAuthAttributes(
                (String) response.get("email"),
                (String) response.get("name")
        );
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
