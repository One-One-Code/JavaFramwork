package com.example.demo.controller;

import com.example.demo.JwtHelper;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.UserDto;
import com.example.demo.filter.AllowAnonymous;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/login")
@RestController
public class LoginController {

    @AllowAnonymous
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody LoginRequest loginRequest) {
        if (loginRequest != null && !StringUtils.hasText(loginRequest.getUserName())) {
            return "";
        }
        UserDto user = new UserDto();
        user.setUserId(12);
        user.setUserName("huangping");
        return JwtHelper.createToken(user);
    }
}
