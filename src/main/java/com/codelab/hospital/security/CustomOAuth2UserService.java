package com.codelab.hospital.security;

import com.codelab.hospital.user.domain.User;
import com.codelab.hospital.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

/**
 * OAuth2 로그인 성공 시 사용자 정보를 받아와 DB에 저장하거나 기존 회원을 조회합니다.
 */
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) {
        OAuth2User oAuth2User = super.loadUser(request);

        String provider = request.getClientRegistration().getRegistrationId();  // google, kakao, naver
        Map<String, Object> attributes = oAuth2User.getAttributes();

        OAuthAttributes oAuthAttributes = OAuthAttributes.of(provider, attributes);

        // 이메일로 기존 회원 조회 또는 생성
        User user = userRepository.findByUsername(oAuthAttributes.getEmail())
                .orElseGet(() -> userRepository.save(
                        User.createUser(oAuthAttributes.getEmail(), "oauth", oAuthAttributes.getName(), "01000000000", java.time.LocalDate.of(1990,1,1))
                ));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRole())),
                attributes,
                "email"
        );
    }
}
