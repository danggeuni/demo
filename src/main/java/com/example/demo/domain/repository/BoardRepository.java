package com.example.demo.domain.repository;

import com.example.demo.domain.BoardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public BoardEntity insertArticle(BoardEntity e) {

        if (e.getGroupId() == null) {
            jdbcTemplate.update("INSERT INTO BOARD (NAME, TITLE, CONTENTS, SORT_KEY, DEPTH, WRITE_DATE) VALUES (?, ?, ?, ?, ?, ?)",
                    e.getName(), e.getTitle(), e.getContent(), e.getSortKey(), e.getDepth(), e.getCreateDate());

            jdbcTemplate.update("UPDATE BOARD SET GROUP_ID = ID WHERE ID = LAST_INSERT_ID()");
        } else {
            jdbcTemplate.update("UPDATE BOARD SET SORT_KEY = SORT_KEY + 1 WHERE GROUP_ID = ? AND SORT_KEY >= ?",
                    e.getGroupId(), e.getSortKey());

            jdbcTemplate.update("INSERT INTO BOARD (NAME, TITLE, CONTENTS, GROUP_ID, SORT_KEY, DEPTH, WRITE_DATE) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    e.getName(), e.getTitle(), e.getContent(), e.getGroupId(), e.getSortKey(), e.getDepth(), e.getCreateDate());
        }
        return e;
    }

    public BoardEntity getArticle(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM BOARD WHERE ID = ?", new Object[]{id}, boardEntityRowMapper());
    }

    public List<BoardEntity> getArticles(int pageSize, int pageNumber) {
        int offset = (pageNumber - 1) * pageSize;
        return jdbcTemplate.query("SELECT * FROM BOARD ORDER BY GROUP_ID DESC, SORT_KEY LIMIT ? OFFSET ?", new Object[]{pageSize, offset}, boardEntityRowMapper());
    }

    public int getPages(int pageSize){
        int count = jdbcTemplate.queryForObject("SELECT COUNT(ID) FROM BOARD", Integer.class);
        return (int) Math.ceil((double) count / pageSize);
    }


    RowMapper<BoardEntity> boardEntityRowMapper() {
        return ((rs, rowNum) -> new BoardEntity(
                rs.getLong("ID"),
                rs.getString("NAME"),
                rs.getString("TITLE"),
                rs.getString("CONTENTS"),
                rs.getLong("GROUP_ID"),
                rs.getInt("SORT_KEY"),
                rs.getInt("DEPTH"),
                rs.getTimestamp("WRITE_DATE").toLocalDateTime(),
                rs.getTimestamp("UPDATE_DATE").toLocalDateTime()
        ));
    }
}
