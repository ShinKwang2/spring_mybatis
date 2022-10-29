package com.ssafy.board.controller;

import com.ssafy.board.model.dto.Board;
import com.ssafy.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping("/")
    public String showIndex() {
        return "redirect:list";
    }

    @RequestMapping("list")
    public String list(Model model) {
        List<Board> list = boardService.getBoardList();
        model.addAttribute("list", list);

        return "/board/list";
    }

    @RequestMapping("writeform")
    public String writeForm() {
        return "/board/writeForm";
    }

    @RequestMapping("write")
    public String write(@ModelAttribute Board board) {
        boardService.writeBoard(board);
        return "redirect:detail?id=" + board.getId();
    }

    @RequestMapping("detail")
    public String detail(Model model, int id) {
        Board b = boardService.readBoard(id);
        model.addAttribute("board", b);
        return "/board/detail";
    }

    @RequestMapping("delete")
    public String delete(int id) {
        boardService.removeBoard(id);
        return "redirect:list";
    }
}
