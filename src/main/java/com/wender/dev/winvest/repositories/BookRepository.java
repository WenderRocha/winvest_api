package com.wender.dev.winvest.repositories;

import com.wender.dev.winvest.entities.Book;
import com.wender.dev.winvest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT obj FROM Book obj WHERE obj.date =:date")
    Book findByDate(@Param("date") LocalDate date);
}
