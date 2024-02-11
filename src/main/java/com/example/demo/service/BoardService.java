package com.example.demo.service;

import com.example.demo.controller.board.AddBoardRequest;
import com.example.demo.domain.BoardEntity;
import com.example.demo.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardEntity insertBoard(AddBoardRequest request){
        return boardRepository.insertBoard(request.toEntity());
    }
}
