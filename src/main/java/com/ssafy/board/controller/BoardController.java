package com.ssafy.board.controller;

import com.ssafy.board.model.dto.Board;
import com.ssafy.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String showIndex() {
        return "redirect:list";
    }

    @GetMapping("list")
    public String list(Model model) {
        List<Board> list = boardService.getBoardList();
        model.addAttribute("list", list);

        return "/board/list";
    }

    @GetMapping("writeform")
    public String writeForm() {
        return "/board/writeForm";
    }

    @PostMapping("write")
    public String write(@ModelAttribute Board board) {
        boardService.writeBoard(board);
        return "redirect:detail?id=" + board.getId();
    }

    @GetMapping("detail")
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
