package me.kalpha.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter @Setter
@RequiredArgsConstructor
public class BookAuthor {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private Integer seq;
}
