package me.kalpha.book.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Getter @Setter
@RequiredArgsConstructor
public class Book {
    @Id @GeneratedValue
    private BigInteger id;
    @NotEmpty
    private String title;
    private LocalDateTime created;
    @OneToMany(mappedBy = "book")
    private List<BookAuthor> bookAuthors;
}
