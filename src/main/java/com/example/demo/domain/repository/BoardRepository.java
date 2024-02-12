package com.example.demo.domain.repository;

import com.example.demo.domain.BoardEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    public BoardRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public BoardEntity insertBoard(BoardEntity entity){
        if(entity.getGroup() == null){
            jdbcTemplate.update("INSERT INTO BOARD (NAME, TITLE, CONTENTS, SORT_KEY, DEPTH) VALUES (?, ?, ?, ?, ?)",
                    entity.getName(), entity.getTitle(), entity.getContent(), 0, 0);

            jdbcTemplate.update("UPDATE BOARD SET GROUP_ID = ID WHERE ID = LAST_INSERT_ID()");
        }else {
//            만들어진 데이터보다 sortKey 값이 같거나 크면 기존 데이터 업데이트
            jdbcTemplate.update("UPDATE BOARD SET SORT_KEY = SORT_KEY + 1 WHERE GROUP_ID = ? AND SORT_KEY >= ?",
                    entity.getGroup(), entity.getSortKey());

            jdbcTemplate.update("INSERT INTO BOARD (NAME, TITLE, CONTENTS, GROUP_ID, SORT_KEY, DEPTH) VALUES (?, ?, ?, ?, ?, ?)",
                    entity.getName(), entity.getTitle(), entity.getContent(), entity.getGroup(),entity.getSortKey(), entity.getDepth());
        }
        return entity;
    }

    public List<BoardEntity> getBoardList(){
        return jdbcTemplate.query("SELECT * FROM BOARD ORDER BY GROUP_ID DESC, SORT_KEY", boardEntityRowMapper());
    }

    public BoardEntity getBoard(Long parentId){
        return jdbcTemplate.queryForObject("SELECT * FROM BOARD WHERE ID = ?", new Object[]{parentId}, boardEntityRowMapper());
    }

    RowMapper<BoardEntity> boardEntityRowMapper(){
        return ((rs, rowNum) -> new BoardEntity(
                rs.getLong("ID"),
                rs.getString("NAME"),
                rs.getString("TITLE"),
                rs.getString("CONTENTS"),
                rs.getLong("GROUP_ID"),
                rs.getInt("SORT_KEY"),
                rs.getInt("DEPTH"),
                rs.getTimestamp("WRITE_DATE").toLocalDateTime(),
                rs.getTimestamp("UPDATE_DATE").toLocalDateTime()));
    }
}
