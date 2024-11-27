package org.example.springplusteam.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    USER_NOT_FOUND(404, "유저 정보를 찾을 수 없습니다."),
    PASSWORD_MISS_MATCH(400, "패스워드가 일치하지 않습니다."),
    TOKEN_NOT_FOUND(401, "토큰 정보를 찾을 수 없습니다."),
    INVALID_TOKEN_ERROR(403, "토큰 정보가 잘못되었습니다."),
    EXISTS_EMAIL(400, "존재하는 이메일 입니다."),
    PRODUCT_NOT_FOUND(404, "상품을 찾을 수 없습니다."),
    ORDER_NOT_FOUND(404, "해당 주문을 찾을 수 없습니다."),
    ORDER_BAD_REQUEST(400, "잘못된 주문 상태 변경 요청입니다."),
    TICKET_NOT_FOUND(404, "티켓을 찾을 수 없습니다."),
    NO_SEAT_AVAILABLE(400, "남아있는 좌석이 없습니다."),
    ALREADY_RESERVE_SEAT(400, "이미 예약되어있는 좌석입니다."),
    ;


    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
