package com.ssafy.board.service;

import com.ssafy.board.model.dao.BoardDao;
import com.ssafy.board.model.dto.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private BoardDao boardDao;

    @Autowired
    public BoardServiceImpl(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Override
    public List<Board> getBoardList() {
        System.out.println("모든 게시글을 가져온다.");
        return boardDao.selectAll();
    }

    @Override
    public Board readBoard(int id) {
        System.out.println(id + "번 글을 읽습니다.");
        boardDao.updateViewCnt(id);
        return boardDao.selectOne(id);
    }

    @Override
    public void writeBoard(Board board) {
        System.out.println("게시글을 작성합니다.");
        boardDao.insertBoard(board);
    }

    @Override
    public void modifyBoard(Board board) {
        System.out.println("게시글 수정합니다.");
        boardDao.updateBoard(board);
    }

    @Override
    public void removeBoard(int id) {
        System.out.println("게시글을 삭제합니다.");
        boardDao.deleteBoard(id);
    }
}
