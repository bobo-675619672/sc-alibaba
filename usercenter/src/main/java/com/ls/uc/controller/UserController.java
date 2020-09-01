package com.ls.uc.controller;
import com.ls.uc.entity.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api( "用户")
@RestController
@RequestMapping("/user")
public class UserController {

    @ApiOperation(value = "登录")
    @GetMapping("/login")
    public String query(@Valid  @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            return "no";
        }
        return "yes";
    }
}
