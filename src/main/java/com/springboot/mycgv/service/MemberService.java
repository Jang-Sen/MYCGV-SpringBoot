package com.springboot.mycgv.service;

import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.dto.PageDto;
import com.springboot.mycgv.dto.SessionDto;
import com.springboot.mycgv.repository.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberMapper memberMapper;

    /*
    join
     */
    public int join(MemberDto memberDto){
        return memberMapper.join(memberDto);
    }

    /*
    idCheck
     */
    public int id_check(String id){
        return memberMapper.id_check(id);
    }

    /*
    login
     */
    public SessionDto login(MemberDto memberDto){
        return memberMapper.login(memberDto);
    }

    /**
     * list
     */
    public List<MemberDto> list(PageDto pageDto){
        return memberMapper.list(pageDto);
    }
}
