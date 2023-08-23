package com.springboot.mycgv.controller;

import com.springboot.mycgv.dto.BoardDto;
import com.springboot.mycgv.dto.PageDto;
import com.springboot.mycgv.service.BoardService;
import com.springboot.mycgv.service.MemberService;
import com.springboot.mycgv.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MycgvRestController {
    @Autowired
    MemberService memberService;
    @Autowired
    BoardService boardService;
    @Autowired
    PageService pageService;

    @GetMapping("id_check/{id}")
    public String id_check(@PathVariable String id){
        // int result = memberService.idCheck(id);

        return String.valueOf(memberService.id_check(id));
    }

    @GetMapping("board_list_json_data/{page}")
    public Map board_list_json_data(@PathVariable String page){
        Map map = new HashMap();
        PageDto pageDto = pageService.getPageResult(new PageDto(page, "board"));
        List<BoardDto> list = boardService.list(pageDto);

        map.put("list", list);
        map.put("page", pageDto);

        return map;
    }

    @GetMapping("board_content_json_data/{bid}")
    public BoardDto board_content_json_data(@PathVariable String bid){
        return boardService.content(bid);
    }
}
