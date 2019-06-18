package org.crazyit.book.dao;

import java.util.List;

import org.crazyit.book.entity.StoreItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StoreItemDao extends JpaRepository<StoreItem, Integer> {

    List<StoreItem> findByStoreId(Integer storeId);
}
