package com.example.medimateserver.util;

import com.example.medimateserver.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static ResponseEntity<?> success() {
        ResponseDto.gI().setMessage(HttpStatus.OK.getReasonPhrase());
        ResponseDto.gI().setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(GsonUtil.gI().toJson(ResponseDto.gI()), HttpStatus.OK);
    }
    public static ResponseEntity<?> success(String mess) {
        return new ResponseEntity<>(mess, HttpStatus.OK);
    }

    public static ResponseEntity<?> failed() {
        ResponseDto.gI().setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        ResponseDto.gI().setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(GsonUtil.gI().toJson(ResponseDto.gI()), HttpStatus.BAD_REQUEST);
    }
    public static ResponseEntity<?> failedExpriration() {
        ResponseDto.gI().setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase() + " token is expriration");
        ResponseDto.gI().setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(GsonUtil.gI().toJson(ResponseDto.gI()), HttpStatus.BAD_REQUEST);
    }
}
