package me.kalpha.book.entity;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
public class Book {
    @Id @GeneratedValue
    private Long id;

    @NonNull
    private String title;

    private LocalDateTime created;

    @OneToMany(mappedBy = "book")
    private List<BookAuthor> bookAuthors;
}
