package com.fridge2fork.global.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fridge2fork.global.exception.BaseCode;
import com.fridge2fork.global.exception.BaseErrorCode;
import com.fridge2fork.global.exception.ResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@Getter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)   // Null인 필드는 JSON 결과물에서 제외해라.
public class ApiResponse<T> {
    private final boolean success;
    private final String Code;
    private final String Message;
    private final T Data;

    /**
     * 성공 응답 생성.
     * 컨트롤러에서 ApiResponse.onSuccess(SuccessCode.RECIPE_RECOMMEND_SUCCESS, result) 형태로 사용.
     *
     * @param code    SuccessCode Enum (BaseCode 구현체)
     * @param payload 실제 응답 데이터
     */
    public static <T> ResponseEntity<ApiResponse<T>> onSuccess(BaseCode code, T payload) {
        // 1. Enum에 정의된 HTTP 상태, 커스텀 코드, 메시지를 추출
        ResponseDto reason = code.getReasonHttpStatus();
        // 2. ResponseEntity를 생성하여 반환
        return ResponseEntity
                .status(reason.getHttpStatus())
                .body(new ApiResponse<>(
                        true, // success 여부: true
                        reason.getCode(), // 서비스 커스텀 코드 (예: "RECIPE200")
                        reason.getMessage(), // 응답 메시지 (예: "레시피 조회 성공")
                        payload)); // 실제 클라이언트가 필요로 하는 데이터(결과값)
    }

    /**
     * 실패 응답 생성.
     * GlobalExceptionHandler에서 ApiResponse.onFailure(ErrorCode.RECIPE_NOT_FOUND, null) 형태로 사용.
     * payload를 받긴 하지만 실패 시엔 data를 null로 고정 — 에러 상세는 message/code로만 전달.
     *
     * @param code    ErrorCode Enum (BaseErrorCode 구현체)
     * @param payload 사용하지 않음 (null 고정). @Valid 에러 상세는 별도 처리.
     */
    public static <T> ResponseEntity<ApiResponse<T>> onFailure(BaseErrorCode code, T payload) {
        ResponseDto reason = code.getReasonHttpStatus();
        return ResponseEntity
                .status(reason.getHttpStatus())
                .body(new ApiResponse<>(
                        false,
                        reason.getCode(),
                        reason.getMessage(),
                        null));
    }
}
