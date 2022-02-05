package com.example.timeleaf.domain.enums; //ENUM 타입이 뭔지 질문

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor //? NoAlgsConstructor와 차이
public enum BoardType {
    notice("공지사항"),
    free("자유게시판");

    private String value;
    //이 enum의 역할이 너무 궁금하다

}
