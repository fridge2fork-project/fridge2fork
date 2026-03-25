package com.fridge2fork.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode implements BaseErrorCode {

    // ─── Common ───────────────────────────────────────────────
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "COMMON_4001", "입력값이 올바르지 않습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON_4010", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON_4030", "접근 권한이 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON_4040", "요청한 리소스를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "COMMON_4050", "허용되지 않는 HTTP 메서드입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_5000", "서버 내부 오류가 발생했습니다."),

    // ─── Member ───────────────────────────────────────────────
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER_4090", "이미 사용 중인 이메일입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_4041", "존재하지 않는 회원입니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "MEMBER_4011", "비밀번호가 일치하지 않습니다."),
    SOCIAL_ACCOUNT_CONFLICT(HttpStatus.CONFLICT, "MEMBER_4091", "동일한 이메일로 가입된 다른 소셜 계정이 존재합니다."),

    // ─── JWT / Token ──────────────────────────────────────────
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "TOKEN_4011", "유효하지 않은 토큰입니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "TOKEN_4012", "만료된 토큰입니다."),
    TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "TOKEN_4013", "토큰이 존재하지 않습니다."),
    REFRESH_TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "TOKEN_4014", "유효하지 않은 리프레시 토큰입니다."),
    BLACKLISTED_TOKEN(HttpStatus.UNAUTHORIZED, "TOKEN_4015", "로그아웃 처리된 토큰입니다."),

    // ─── OAuth2 ───────────────────────────────────────────────
    OAUTH2_PROVIDER_NOT_SUPPORTED(HttpStatus.BAD_REQUEST, "OAUTH_4001", "지원하지 않는 소셜 로그인 제공자입니다."),
    OAUTH2_TOKEN_EXCHANGE_FAILED(HttpStatus.BAD_GATEWAY, "OAUTH_5020", "소셜 로그인 토큰 교환에 실패했습니다."),
    OAUTH2_USER_INFO_FAILED(HttpStatus.BAD_GATEWAY, "OAUTH_5021", "소셜 유저 정보 조회에 실패했습니다."),

    // ─── Inventory (냉장고) ────────────────────────────────────
    FRIDGE_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "INVENTORY_4041", "해당 재료를 냉장고에서 찾을 수 없습니다."),
    INGREDIENT_NOT_FOUND(HttpStatus.NOT_FOUND, "INVENTORY_4042", "존재하지 않는 재료입니다."),
    FRIDGE_ITEM_ALREADY_EXISTS(HttpStatus.CONFLICT, "INVENTORY_4091", "이미 등록된 재료입니다."),
    EXPIRY_ESTIMATE_FAILED(HttpStatus.BAD_GATEWAY, "INVENTORY_5020", "AI 유통기한 추론에 실패했습니다."),

    // ─── Recipe ───────────────────────────────────────────────
    RECIPE_NOT_FOUND(HttpStatus.NOT_FOUND, "RECIPE_4041", "존재하지 않는 레시피입니다."),
    RECIPE_FORBIDDEN(HttpStatus.FORBIDDEN, "RECIPE_4031", "레시피를 수정/삭제할 권한이 없습니다."),
    RECIPE_ALREADY_EXISTS(HttpStatus.CONFLICT, "RECIPE_4091", "동일한 레시피가 이미 존재합니다."),
    ALTERNATIVE_FETCH_FAILED(HttpStatus.BAD_GATEWAY, "RECIPE_5020", "AI 대체 재료 조회에 실패했습니다."),
    NUTRITION_FETCH_FAILED(HttpStatus.BAD_GATEWAY, "RECIPE_5021", "영양 정보 조회에 실패했습니다."),

    // ─── Challenge (냉파 챌린지) ───────────────────────────────
    CHALLENGE_INGREDIENT_NOT_ENOUGH(HttpStatus.BAD_REQUEST, "CHALLENGE_4001", "챌린지를 시작하려면 유통기한 임박 재료가 3개 이상 필요합니다."),
    CHALLENGE_ALREADY_COMPLETED(HttpStatus.CONFLICT, "CHALLENGE_4091", "이미 완료된 챌린지입니다."),

    // ─── Community ────────────────────────────────────────────
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMUNITY_4041", "존재하지 않는 댓글입니다."),
    COMMENT_FORBIDDEN(HttpStatus.FORBIDDEN, "COMMUNITY_4031", "댓글을 수정/삭제할 권한이 없습니다."),
    LIKE_ALREADY_EXISTS(HttpStatus.CONFLICT, "COMMUNITY_4091", "이미 좋아요를 누른 레시피입니다."),

    // ─── Diet (식단/기록) ──────────────────────────────────────
    MEAL_LOG_NOT_FOUND(HttpStatus.NOT_FOUND, "DIET_4041", "존재하지 않는 식사 기록입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ResponseDto getReasonHttpStatus() {
        return ResponseDto.builder()
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .isSuccess(false)
                .build();
    }
}