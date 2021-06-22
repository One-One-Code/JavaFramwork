package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import repository.IUserRepository;
import repository.UserRepository;
import repository.po.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequestMapping("/user")
@RestController
public class UserController {

    @ResponseBody
    @RequestMapping(value = "/getUserName", method = RequestMethod.GET)
    public String getUserName() {
        return "huangping";
    }

    @ResponseBody
    @RequestMapping(value = "/getUser/{param}", method = RequestMethod.GET)
    public UserDto getUser(@PathVariable("param") int userId) {
        IUserRepository userRepository = new UserRepository();
        UserInfo userPo = userRepository.getUser(userId);
        if (userPo == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setUserName(userPo.getUserName());
        userDto.setLastLoginTime(userPo.getLastLoginTime());
        return userDto;
    }

    @ResponseBody
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public UserDto createUser(@RequestBody UserDto userDto) {
        UserInfo userInfo=new UserInfo();
        userInfo.setUserName(userDto.getUserName());
        userInfo.setPassword("123456");
        IUserRepository userRepository = new UserRepository();
        userRepository.addUser(userInfo);
        return userDto;
    }

    @ResponseBody
    @RequestMapping(value = "/createTestUser", method = RequestMethod.POST)
    public int createTestUser(){
        List<UserInfo> userInfoList=new ArrayList<>();
        for (int i=0;i<100;i++){
            UserInfo userInfo=new UserInfo();
            userInfo.setUserName(String.format("huangping：%d",i));
            userInfo.setPassword("123456");
            userInfoList.add(userInfo);
        }
        IUserRepository userRepository = new UserRepository();
        return userRepository.addUserMulti(userInfoList);

    }

}
