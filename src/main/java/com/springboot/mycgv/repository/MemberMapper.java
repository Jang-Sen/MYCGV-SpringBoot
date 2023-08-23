package com.springboot.mycgv.repository;

import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.dto.PageDto;
import com.springboot.mycgv.dto.SessionDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    int join(MemberDto memberDto);
    int id_check(String id);
    SessionDto login(MemberDto memberDto);
    List<MemberDto> list(PageDto pageDto);
}
