package me.kalpha.book.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table
@Getter @Setter
@RequiredArgsConstructor
public class BookAuthor {
    @Id @GeneratedValue
    private BigInteger id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private Integer seq;
}
