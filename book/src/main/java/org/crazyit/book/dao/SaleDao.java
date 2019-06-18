package org.crazyit.book.dao;

import org.crazyit.book.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleDao extends JpaRepository<Sale, Integer> {

}
