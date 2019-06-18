package org.crazyit.book.dao;

import java.util.List;

import org.crazyit.book.entity.Book;
import org.crazyit.book.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreDao extends JpaRepository<Store, Integer> {

    
}
