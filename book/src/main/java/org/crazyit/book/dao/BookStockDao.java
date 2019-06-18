package org.crazyit.book.dao;

import java.util.List;

import org.crazyit.book.entity.BookStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookStockDao extends JpaRepository<BookStock, Integer> {

    BookStock findByBookId(Integer bookId);

    // 查询前10条存库数据，根据id排序
    List<BookStock> findTop10ByOrderByIdDesc();
}
