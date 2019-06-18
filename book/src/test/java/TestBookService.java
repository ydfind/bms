import org.crazyit.book.BookApp;
import org.crazyit.book.entity.Book;
import org.crazyit.book.service.BookService;
import org.crazyit.book.service.SaleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
//@SpringBootTest(class = BookApp.class)
@SpringBootTest(classes = BookApp.class)
public class TestBookService {

    @Autowired
    public BookService bookService;

    @Test
    public void testSave(){
        Book book = new Book();
        book.setName("简单，应对复杂世界的利器");
        book.setAuthor("Anling");
        book.setCover("D://dafw");
        book.setPrice(new BigDecimal(36.80));
        book.setIsDelete(false);
        bookService.save(book);

    }
}
