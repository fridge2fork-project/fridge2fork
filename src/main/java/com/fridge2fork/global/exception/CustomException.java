package com.fridge2fork.global.exception;

import lombok.Getter;

/**
 * 비즈니스 로직에서 의도적으로 던지는 커스텀 예외.
 * ErrorCode Enum을 그대로 품고 있어, Handler에서 꺼내쓰기 쉬움.
 * 사용 예시:
 *   throw new CustomException(ErrorCode.RECIPE_NOT_FOUND);
 */
@Getter
public class CustomException extends RuntimeException {

    private final BaseErrorCode errorCode;

    public CustomException(BaseErrorCode errorCode) {
        // RuntimeException의 message도 채워줌 → 로그에서 e.getMessage()로 바로 확인 가능
        super(errorCode.getReasonHttpStatus().getMessage());
        this.errorCode = errorCode;
    }
}