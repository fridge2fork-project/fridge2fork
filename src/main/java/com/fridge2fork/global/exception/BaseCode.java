package com.fridge2fork.global.exception;

/**
 * 성공 코드 Enum(SuccessCode)이 구현해야 할 인터페이스.
 * SuccessCode → BaseCode → getReasonHttpStatus() → ResponseDto
 * ApiResponse.onSuccess()가 이 인터페이스만 바라보므로,
 * 새로운 성공 코드 Enum을 추가해도 ApiResponse 코드는 바꿀 필요 없음. (OCP)
 */
public interface BaseCode {
    ResponseDto getReasonHttpStatus();
}
