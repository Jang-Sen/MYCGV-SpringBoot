package com.springboot.mycgv.controller;

import com.springboot.mycgv.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private MemberService memberService;

    @GetMapping("index")
    public String admin_index(){
        return "/admin/admin_index";
    }

    @GetMapping("notice_list")
    public String admin_notice_list(){
        return "/admin/notice/admin_notice_list";
    }

    @GetMapping("member_list/{page}")
    public String admin_member_list(@PathVariable String page, Model model){

        return "/admin/member/admin_member_list";
    }
}
