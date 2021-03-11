package com.homecafe.external.kakao;

import com.homecafe.external.kakao.dto.response.KaKaoUserInfoResponse;

public interface KaKaoApiCaller {

	KaKaoUserInfoResponse getKaKaoUserProfileInfo(String accessToken);

}