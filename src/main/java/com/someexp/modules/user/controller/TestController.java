package com.someexp.modules.user.controller;

import com.someexp.common.domain.Result;
import com.someexp.common.utils.MsgUtils;
import com.someexp.config.ShiroConfig;
import com.someexp.modules.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author someexp
 * @date 2020/11/12
 */
@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ShiroConfig shiroConfig;

    @GetMapping("/red")
    public RedirectView redirectView() {
        return new RedirectView("http://www.baidu.com");
    }

    @GetMapping("/corsTest")
    public ResponseEntity<?> corsTest() {

        return ResponseEntity.status(HttpStatus.OK).body(Result.success(null, MsgUtils.get("test.success")));
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {

        Subject subject = SecurityUtils.getSubject();
        if (subject.isPermitted("hero")) {
            System.out.println("hello");
        }
        if (subject.isPermitted("user")) {
            System.out.println("user");
        }
        if (subject.isPermitted("admin")) {
            System.out.println("admin");
        }
        if (shiroConfig == null) {
            System.out.println("null");
        } else {
            System.out.println("not null");
        }
        return ResponseEntity.status(HttpStatus.OK).body(Result.success(null, MsgUtils.get("test.success")));
    }

    @GetMapping("/auth")
    @Transactional
    public ResponseEntity<?> auth() {
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.isPermitted("user:" + "11"));
        return ResponseEntity.status(HttpStatus.OK).body(Result.success(null, MsgUtils.get("test.success")));
    }

    @GetMapping("/red2")
    public ResponseEntity<?> responseEntity2() {
        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .header("location", "http://baidu.com")
                .body(null);
    }
}
