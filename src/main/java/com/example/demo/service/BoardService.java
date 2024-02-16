package com.example.demo.service;

import com.example.demo.controller.dto.board.AddArticleRequest;
import com.example.demo.controller.dto.board.FileResponse;
import com.example.demo.controller.dto.board.ViewPagesResponse;
import com.example.demo.domain.BoardEntity;
import com.example.demo.domain.FileEntity;
import com.example.demo.domain.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public BoardEntity insetArticle(AddArticleRequest request, Long parentId) {

        if(request.getName().isEmpty() || request.getTitle().isEmpty() || request.getContent().isEmpty()){
            throw new IllegalStateException("빈칸이 존재합니다.");
        }

        if (parentId == null) {
            return boardRepository.insertArticle(request.toEntity());
        } else {
            BoardEntity parentData = boardRepository.getArticle(parentId);

            if(parentData == null){
                throw new IllegalStateException("원글이 존재하지 않습니다.");
            }

            return boardRepository.insertArticle(new BoardEntity(
                    null,
                    request.getName(),
                    request.getTitle(),
                    request.getContent(),
                    parentData.getGroupId(),
                    parentData.getSortKey() + 1,
                    parentData.getDepth() + 1,
                    LocalDateTime.now(),
                    null)
            );
        }
    }

    public BoardEntity getArticle(Long id){
        return boardRepository.getArticle(id);
    }

    public ViewPagesResponse<BoardEntity> getArticles(int pageSize, int pageNumber){
        List<BoardEntity> data = boardRepository.getArticles(pageSize, pageNumber);
        int pages = boardRepository.getPages(pageSize);

        return new ViewPagesResponse<>(data, pages);
    }

    public void uploadFile(String path, String originFileName, String downFileName, Long id){
        boardRepository.uploadFile(path, originFileName, downFileName, id);
    }

    public FileEntity findFileById(Long parentId){
        return boardRepository.findFileById(parentId);
    }
}

