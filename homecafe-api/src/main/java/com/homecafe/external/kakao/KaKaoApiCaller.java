package com.homecafe.external.kakao;

import com.homecafe.external.kakao.dto.response.KaKaoAccessTokenResponse;
import com.homecafe.external.kakao.dto.response.KaKaoUserInfoResponse;

public interface KaKaoApiCaller {

	KaKaoAccessTokenResponse getKaKaoAccessToken(String code, String redirectUri);

	KaKaoUserInfoResponse getKaKaoUserProfileInfo(String accessToken);

}