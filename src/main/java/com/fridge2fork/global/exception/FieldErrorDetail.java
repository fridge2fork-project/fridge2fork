package com.fridge2fork.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class FieldErrorDetail {
    private final String field;          // 실패한 필드명
    private final Object rejectedValue;  // 실제로 들어온 잘못된 값
    private final String reason;         // @NotBlank, @Email 등의 메시지
}