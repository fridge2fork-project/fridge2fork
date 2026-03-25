package com.fridge2fork.global.exception;

/**
 * 에러 코드 Enum(ErrorCode)이 구현해야 할 인터페이스.
 * BaseCode와 동일한 이유로 분리. (성공/실패 타입 혼용 방지)
 * ApiResponse.onFailure()와 GlobalExceptionHandler가 이 타입만 받도록 강제함.
 */
public interface BaseErrorCode {
    ResponseDto getReasonHttpStatus();
}
