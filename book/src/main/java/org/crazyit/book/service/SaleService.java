package org.crazyit.book.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.crazyit.book.dao.BookDao;
import org.crazyit.book.dao.BookStockDao;
import org.crazyit.book.dao.SaleDao;
import org.crazyit.book.dao.SaleItemDao;
import org.crazyit.book.entity.Book;
import org.crazyit.book.entity.BookStock;
import org.crazyit.book.entity.Sale;
import org.crazyit.book.entity.SaleItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class SaleService {

    @Autowired
    private SaleDao saleDao;
    
    @Autowired
    private SaleItemDao saleItemDao;
    
    @Autowired
    private BookDao bookDao;
    
    @Autowired
    private BookStockService bookStockService;
    
    /**
     * 分页查询数据
     */
    public Page<Sale> findAll(Pageable pageable) {
        Page<Sale> datas = saleDao.findAll(pageable);
        // 设置明细的书本名称
        List<Sale> sales = datas.getContent();
        for(Sale s : sales) {
            List<SaleItem> items = saleItemDao.findBySaleId(s.getId());
            s.initBookNames(items);
            s.initMoney(items);
        }
        return datas;
    }
    
    /**
     * 创建销售单
     */
    @Transactional
    public void save(List<SaleItem> items) {
        // 保存销售单
        Sale sale = new Sale();
        sale.setCreateTime(new Date());
        saleDao.save(sale);
        // 保存明细
        for(int i = 0; i < items.size(); i++) {
            SaleItem item = items.get(i);
            // 查询 Book 对象
            Book book = bookDao.findById(item.getBook().getId()).get();
            item.setSale(sale);
            item.setBook(book);
            item.setPrice(book.getPrice());
            saleItemDao.save(item);
            // 更新库存
            bookStockService.substractStock(book.getId(), item.getAmount());
        }
    }
    
    /**
     * 根据id查询Sale
     */
    public Sale findById(Integer saleId) {
        return saleDao.findById(saleId).get();
    }
    
    /**
     * 根据id查询销售单明细
     */
    public List<SaleItem> findBySaleId(Integer saleId) {
        return saleItemDao.findBySaleId(saleId);
    }
}
