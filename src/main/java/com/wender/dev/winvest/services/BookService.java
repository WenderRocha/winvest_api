package com.wender.dev.winvest.services;

import com.wender.dev.winvest.entities.Book;
import com.wender.dev.winvest.entities.Management;
import com.wender.dev.winvest.repositories.BookRepository;
import com.wender.dev.winvest.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public Book findById(Long id) {
        Optional<Book> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! id: " + id));
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public Book create(Management management) {
        Book book = new Book();

        book.setDate(LocalDate.now());
        book.setWin(management.getWinToday());
        book.setLoss(management.getLossToday());
        book.setProfit(management.getProfitToday());
        book.setBalance(management.getBalance());
        book.setManagement(management);

        return repository.save(book);
    }

    public Book update(Management management) {
        Book oldObj = findByDate(LocalDate.now());

        oldObj.setWin(management.getWinToday());
        oldObj.setLoss(management.getLossToday());
        oldObj.setProfit(management.getProfitToday());
        oldObj.setBalance((management.getBalance()));

        return repository.save(oldObj);
    }

    public void delete(Long id) {
        Book book = findById(id);
        repository.deleteById(id);
    }

    public Book findByDate(LocalDate date) {
        Book book = repository.findByDate(date);

        if (book != null) {
            return book;
        }

        return null;
    }
}
