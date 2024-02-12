package com.example.demo.service;

import com.example.demo.controller.dto.AddBoardRequest;
import com.example.demo.domain.BoardEntity;
import com.example.demo.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardEntity insertBoard(AddBoardRequest request, Long parentId) {

        System.out.println(request.getName());
        System.out.println(request.getTitle());
        System.out.println(request.getContent());

        if (parentId == null) {
            return boardRepository.insertBoard(request.toEntity());
        } else {
//            부모 데이터를 가져온 뒤 childData를 생성한다. 부모의 sortKey + 1, depth + 1
            BoardEntity parentData = boardRepository.getBoard(parentId);

            if(parentData == null){
                throw new IllegalStateException("해당 글을 찾을 수 없습니다.");
            }

            BoardEntity childData = new BoardEntity(null, request.getName(), request.getTitle(), request.getContent(),
                    parentData.getGroup(), parentData.getSortKey() + 1, parentData.getDepth() + 1, null, null);
            return boardRepository.insertBoard(childData);
        }
    }

    public BoardEntity getBoard(Long id){
        return boardRepository.getBoard(id);
    }

    public List<BoardEntity> getBoardList(){
        return boardRepository.getBoardList();
    }
}
