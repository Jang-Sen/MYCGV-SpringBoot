package com.springboot.mycgv.repository;

import com.springboot.mycgv.dto.BoardDto;
import com.springboot.mycgv.dto.PageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardDto> list(PageDto pageDto);
    BoardDto content(String bid);
    int update(BoardDto boardDto);
    int insert(BoardDto boardDto);
    String getOldFile(String bid);
    int delete(String bid);
}
