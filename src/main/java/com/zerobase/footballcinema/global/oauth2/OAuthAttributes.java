package com.zerobase.footballcinema.global.oauth2;

import static com.zerobase.footballcinema.member.type.MemberType.*;

import com.zerobase.footballcinema.member.domain.Member;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OAuthAttributes {
  private final Map<String, Object> attributes; // OAuth2 반환하는 유저 정보 Map
  private final String nameAttributeKey;
  private final String name;
  private final String email;

  public static OAuthAttributes of(String registrationId,
      String userNameAttributeName,
      Map<String, Object> attributes){
      return ofKakao(attributes);
  }

  private static OAuthAttributes ofKakao(Map<String, Object> attributes) {
    // kakao는 kakao_account에 유저정보가 있다. (email)
    Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
    // kakao_account안에 또 profile이라는 JSON객체가 있다. (nickname, profile_image)
    Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");

    return OAuthAttributes.builder()
        .name((String) kakaoProfile.get("nickname"))
        .email((String) kakaoAccount.get("email"))
        .attributes(attributes)
        .nameAttributeKey("id")
        .build();
  }

  public Member toEntity(){
    return Member.builder()
        .username(name)
        .email(email)
        .memberType(USER)
        .build();
  }
}
