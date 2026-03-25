package com.fridge2fork.global.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * BaseCode / BaseErrorCode 가 반환하는 공통 응답 정보 DTO.
 * Enum에서 직접 HttpStatus, code, message를 들고 다니는 대신
 * 이 DTO로 한 번 포장해서 반환함.
 *
 * isSuccess 필드를 추가한 이유:
 *   - ApiResponse 생성 시 성공/실패 여부를 Enum 타입이 아닌 DTO 레벨에서도 알 수 있게 함.
 *   - GlobalExceptionHandler에서 ResponseDto만 꺼내도 판단 가능.
 */
@Getter
@Builder
public class ResponseDto {
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    private final boolean isSuccess; // 성공이면 true, 실패면 false
}