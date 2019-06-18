package org.crazyit.book.dao;

import java.util.List;

import org.crazyit.book.entity.Book;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, Integer> {

    // 查询前3条 isDelete 的数据，并且会根据 id 排序
    List<Book> findTop3ByIsDeleteOrderByIdDesc(boolean isDelete);
}