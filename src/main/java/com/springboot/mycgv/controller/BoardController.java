package com.springboot.mycgv.controller;

import com.springboot.mycgv.dto.BoardDto;
import com.springboot.mycgv.dto.PageDto;
import com.springboot.mycgv.service.BoardService;
import com.springboot.mycgv.service.FileUploadService;
import com.springboot.mycgv.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private PageService pageService;
    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping("board_list/{page}")
    public String board_list(@PathVariable String page, Model model){
        PageDto pageDto = pageService.getPageResult(new PageDto(page, "board"));

        model.addAttribute("list", boardService.list(pageDto));
        model.addAttribute("page", pageDto);

        return "board/board_list";
    }

    @GetMapping("board_list_json")
    public String board_list_json(){
        return "/board/board_list_json";
    }

    @GetMapping("board_content/{bid}/{page}")
    public String board_content(@PathVariable String bid, @PathVariable String page, Model model){
        model.addAttribute("board", boardService.content(bid));
        model.addAttribute("page", page);

        return "board/board_content";
    }

    @GetMapping("board_write")
    public String board_write(){

        return "board/board_write";
    }

    @PostMapping("board_write")
    public String board_write_proc(BoardDto boardDto) throws Exception{
        // 파일 업로드 서비스 추가 & insert
        boardDto = (BoardDto) fileUploadService.fileCheck(boardDto);
        int result = boardService.insert(boardDto);

        if (result == 1) fileUploadService.fileSave(boardDto);

        return "redirect:/board_list/1";
    }

    @GetMapping("board_update/{bid}/{page}")
    public String board_update(@PathVariable String bid, @PathVariable String page, Model model){
        model.addAttribute("board", boardService.content(bid));
        model.addAttribute("page", page);

        return "board/board_update";
    }

    @PostMapping("board_update")
    public String board_update_proc(BoardDto boardDto) throws Exception{
        // 새로운 파일 선택 시 기존 파일(oldFile:bsfile) 삭제
        String oldFile = boardDto.getBsfile();
        boardDto = (BoardDto) fileUploadService.fileCheck(boardDto);
        int result = boardService.update(boardDto);

        if (result == 1){
            if (!boardDto.getFile1().isEmpty()){
                fileUploadService.fileSave(boardDto); // 새로운 파일 저장
                fileUploadService.fileDelete(oldFile); // 기존 파일 삭제
            }
        }

        return "redirect:/board_content/" + boardDto.getBid() + "/" + boardDto.getPage();
    }

    @GetMapping("board_delete/{bid}/{page}")
    public String board_delete(@PathVariable String bid, @PathVariable String page, Model model){
        model.addAttribute("bid", bid);
        model.addAttribute("page", page);

        return "board/board_delete";
    }

    @PostMapping("board_delete")
    public String board_delete_proc(BoardDto boardDto) throws Exception{
        // 파일 포함 0
        String oldFile = boardService.getOldFile(boardDto.getBid());
        int result = boardService.delete(boardDto.getBid());

        if (result == 1){
            if (!oldFile.equals("")){
                fileUploadService.fileDelete(oldFile);
            }
        }

        return "redirect:/board_list/" + boardDto.getPage();
    }
}
