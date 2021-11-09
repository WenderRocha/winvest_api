package com.wender.dev.winvest.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tb_book")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    private BigDecimal win;
    private BigDecimal loss;
    private BigDecimal profit;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "management_id")
    private Management management;

    public Book(Long id, BigDecimal win, BigDecimal loss, BigDecimal profit, Management management) {
        this.id = id;
        this.date = LocalDate.now();
        this.win = management.getWinToday();
        this.loss = management.getLossToday();
        this.profit = management.getProfitToday();
        this.management = management;
    }
}
