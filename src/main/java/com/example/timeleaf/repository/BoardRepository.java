package com.example.timeleaf.repository;

import com.example.timeleaf.domain.Board;
import com.example.timeleaf.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //역할이 뭘까 ? Repository임을 정의해주나?
public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByUser(User user);
}
