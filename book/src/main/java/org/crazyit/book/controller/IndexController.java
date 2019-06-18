package org.crazyit.book.controller;

import org.crazyit.book.entity.User;
import org.crazyit.book.service.BookService;
import org.crazyit.book.service.BookStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class IndexController {
    
    @Autowired
    private BookStockService bookStockService;
    
    @Autowired
    private BookService bookService;

    
    @GetMapping(value = "/main")
    public String main(Model model) {
        model.addAttribute("books", bookService.findIndexBooks());
        model.addAttribute("stocks", bookStockService.findIndexStocks());
        return "main";
    }
}
