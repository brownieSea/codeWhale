package com.codewhale.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("sid")
    public String sid(HttpSession session) {
        // 세션에서 sessionId 속성을 가져와서 sid 변수에 저장
        String sid = (String) session.getAttribute("sessionId");
         return sid;
    }

}
