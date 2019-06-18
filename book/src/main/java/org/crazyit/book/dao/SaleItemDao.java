package org.crazyit.book.dao;

import java.util.List;

import org.crazyit.book.entity.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SaleItemDao extends JpaRepository<SaleItem, Integer> {

    List<SaleItem> findBySaleId(Integer saleId);
}
