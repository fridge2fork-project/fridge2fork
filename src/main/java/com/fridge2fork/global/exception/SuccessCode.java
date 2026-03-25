package com.fridge2fork.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode implements BaseCode {

    // ─── Member ───────────────────────────────────────────────
    REGISTER_SUCCESS(HttpStatus.CREATED, "MEMBER_2001", "회원가입이 완료되었습니다."),
    LOGIN_SUCCESS(HttpStatus.OK, "MEMBER_2002", "로그인에 성공했습니다."),
    OAUTH2_LOGIN_SUCCESS(HttpStatus.OK, "MEMBER_2003", "소셜 로그인에 성공했습니다."),
    TOKEN_REISSUE_SUCCESS(HttpStatus.OK, "MEMBER_2004", "토큰이 재발급되었습니다."),
    LOGOUT_SUCCESS(HttpStatus.OK, "MEMBER_2005", "로그아웃되었습니다."),
    MEMBER_INFO_SUCCESS(HttpStatus.OK, "MEMBER_2006", "회원 정보 조회에 성공했습니다."),
    MEMBER_UPDATE_SUCCESS(HttpStatus.OK, "MEMBER_2007", "회원 정보가 수정되었습니다."),
    MEMBER_DELETE_SUCCESS(HttpStatus.OK, "MEMBER_2008", "회원 탈퇴가 완료되었습니다."),

    // ─── Inventory (냉장고) ────────────────────────────────────
    FRIDGE_ITEM_LIST_SUCCESS(HttpStatus.OK, "INVENTORY_2001", "냉장고 재료 목록 조회에 성공했습니다."),
    FRIDGE_ITEM_ADD_SUCCESS(HttpStatus.CREATED, "INVENTORY_2002", "재료가 등록되었습니다."),
    FRIDGE_ITEM_UPDATE_SUCCESS(HttpStatus.OK, "INVENTORY_2003", "재료 정보가 수정되었습니다."),
    FRIDGE_ITEM_DELETE_SUCCESS(HttpStatus.OK, "INVENTORY_2004", "재료가 삭제되었습니다."),
    EXPIRY_ESTIMATE_SUCCESS(HttpStatus.OK, "INVENTORY_2005", "유통기한 추론에 성공했습니다."),

    // ─── Recipe ───────────────────────────────────────────────
    RECIPE_CREATE_SUCCESS(HttpStatus.CREATED, "RECIPE_2001", "레시피가 등록되었습니다."),
    RECIPE_LIST_SUCCESS(HttpStatus.OK, "RECIPE_2002", "레시피 목록 조회에 성공했습니다."),
    RECIPE_DETAIL_SUCCESS(HttpStatus.OK, "RECIPE_2003", "레시피 상세 조회에 성공했습니다."),
    RECIPE_UPDATE_SUCCESS(HttpStatus.OK, "RECIPE_2004", "레시피가 수정되었습니다."),
    RECIPE_DELETE_SUCCESS(HttpStatus.OK, "RECIPE_2005", "레시피가 삭제되었습니다."),
    RECIPE_RECOMMEND_SUCCESS(HttpStatus.OK, "RECIPE_2006", "재료 기반 레시피 추천에 성공했습니다."),
    MISSING_INGREDIENT_SUCCESS(HttpStatus.OK, "RECIPE_2007", "부족한 재료 조회에 성공했습니다."),
    ALTERNATIVE_INGREDIENT_SUCCESS(HttpStatus.OK, "RECIPE_2008", "대체 재료 조회에 성공했습니다."),

    // ─── Challenge (냉파 챌린지) ───────────────────────────────
    CHALLENGE_FETCH_SUCCESS(HttpStatus.OK, "CHALLENGE_2001", "챌린지 레시피 조회에 성공했습니다."),
    CHALLENGE_COMPLETE_SUCCESS(HttpStatus.OK, "CHALLENGE_2002", "냉장고 파먹기 챌린지를 성공했습니다! 🎉"),

    // ─── Community ────────────────────────────────────────────
    COMMENT_CREATE_SUCCESS(HttpStatus.CREATED, "COMMUNITY_2001", "댓글이 등록되었습니다."),
    COMMENT_LIST_SUCCESS(HttpStatus.OK, "COMMUNITY_2002", "댓글 목록 조회에 성공했습니다."),
    COMMENT_UPDATE_SUCCESS(HttpStatus.OK, "COMMUNITY_2003", "댓글이 수정되었습니다."),
    COMMENT_DELETE_SUCCESS(HttpStatus.OK, "COMMUNITY_2004", "댓글이 삭제되었습니다."),
    LIKE_TOGGLE_SUCCESS(HttpStatus.OK, "COMMUNITY_2005", "좋아요가 처리되었습니다."),
    RANKING_SUCCESS(HttpStatus.OK, "COMMUNITY_2006", "인기 레시피 랭킹 조회에 성공했습니다."),

    // ─── Diet (식단/기록) ──────────────────────────────────────
    MEAL_LOG_CREATE_SUCCESS(HttpStatus.CREATED, "DIET_2001", "식사 기록이 저장되었습니다."),
    MEAL_LOG_LIST_SUCCESS(HttpStatus.OK, "DIET_2002", "식사 기록 조회에 성공했습니다."),
    DAILY_CALORIE_SUCCESS(HttpStatus.OK, "DIET_2003", "일일 칼로리 조회에 성공했습니다."),
    WEEKLY_DIET_PLAN_SUCCESS(HttpStatus.OK, "DIET_2004", "1주일 식단 추천에 성공했습니다.");

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
