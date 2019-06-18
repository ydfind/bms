package org.crazyit.book.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.crazyit.book.dao.BookDao;
import org.crazyit.book.dao.StoreDao;
import org.crazyit.book.dao.StoreItemDao;
import org.crazyit.book.entity.Book;
import org.crazyit.book.entity.BookStock;
import org.crazyit.book.entity.Store;
import org.crazyit.book.entity.StoreItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StoreService {
    
    @Autowired
    private StoreDao storeDao;
    
    @Autowired
    private StoreItemDao storeItemDao;
    
    @Autowired
    private BookDao bookDao;
    
    @Autowired
    private BookStockService bookStockService;

    /**
     * 创建入库单
     * @param items
     */
    @Transactional
    public void save(List<StoreItem> items) {
        // 保存入库单
        Store store = new Store();
        store.setCreateTime(new Date());
        storeDao.save(store);
        // 保存明细
        for(int i = 0; i < items.size(); i++) {
            StoreItem item = items.get(i);
            // 查询 Book 对象
            Book book = bookDao.findById(item.getBook().getId()).get();
            item.setStore(store);
            item.setBook(book);
            storeItemDao.save(item);
            // 更新库存
            bookStockService.addStock(book.getId(), item.getAmount());
        }
    }
    
    /**
     * 分页查询入库单
     */
    public Page<Store> list(Pageable pageable) {
        Page<Store> datas = storeDao.findAll(pageable);
        // 设置明细的书本名称
        List<Store> stores = datas.getContent();
        for(Store s : stores) {
            List<StoreItem> items = storeItemDao.findByStoreId(s.getId());
            s.initAmount(items);
            s.initBookNames(items);
        }
        return datas;
    }
    
    /**
     * 根据id查询Store
     */
    public Store findById(Integer storeId) {
        return storeDao.findById(storeId).get();
    }
    
    /**
     * 根据id查询入库单明细
     */
    public List<StoreItem> findByStoreId(Integer storeId) {
        return storeItemDao.findByStoreId(storeId);
    }
}
