package com.fridge2fork.global.exception;

import com.fridge2fork.global.util.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * [비즈니스 커스텀 예외 처리]
     * Service 계층에서 throw new CustomException(ErrorCode.XXX) 시 호출됨.
     * e.getErrorCode()는 BaseErrorCode 인터페이스를 구현한 Enum(예: ErrorStatus)입니다.
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Void>> handleApiException(CustomException e) {
        log.error("[CustomException] code={}, message={}",
                e.getErrorCode().getReasonHttpStatus().getCode(),
                e.getMessage());
        return ApiResponse.onFailure(e.getErrorCode(), null);
    }

    /**
     * [@Valid 유효성 검사 실패 처리]
     * DTO의 @NotBlank, @Size 등에서 걸린 에러를 상세하게 리스트로 반환합니다.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<List<FieldErrorDetail>>> handleValidationException(MethodArgumentNotValidException ex) {
        log.error("[Validation Exception] 발생 지점: {}", ex.getBindingResult().getObjectName());

        List<FieldErrorDetail> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> FieldErrorDetail.of(
                        error.getField(),
                        error.getRejectedValue(),
                        error.getDefaultMessage()
                ))
                .toList();

        ResponseDto reason = ErrorCode.INVALID_INPUT.getReasonHttpStatus();
        return ResponseEntity
                .status(reason.getHttpStatus())
                .body(new ApiResponse<>(false, reason.getCode(), reason.getMessage(), fieldErrors));
    }

    /**
     * 지원하지 않는 HTTP 메서드 요청 처리. (405 Method Not Allowed)
     * 예: POST 전용 엔드포인트에 GET 요청이 들어온 경우.
     * 두 번째 핸들러에 없던 케이스 — 첫 번째 핸들러에서 가져옴.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodNotAllowed(
            HttpRequestMethodNotSupportedException e) {

        log.error("[MethodNotAllowed] method={}", e.getMethod());
        return ApiResponse.onFailure(ErrorCode.METHOD_NOT_ALLOWED, null);
    }

    /**
     * 그 외 예상치 못한 모든 예외 처리. (500 Internal Server Error)
     * 운영 중 알 수 없는 예외가 여기에 잡힘.
     * e.getMessage()를 로그에만 남기고 클라이언트엔 내부 서버 오류 메시지만 반환.
     * (스택트레이스를 응답에 포함하면 보안 취약점이 될 수 있음)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        log.error("[InternalServerError] message={}", e.getMessage(), e);
        return ApiResponse.onFailure(ErrorCode.INTERNAL_SERVER_ERROR, null);
    }
}