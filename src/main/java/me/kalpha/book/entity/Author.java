package me.kalpha.book.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table
@Getter @Setter
@RequiredArgsConstructor
public class Author {
    @Id @GeneratedValue
    private BigInteger id;
    @NotEmpty
    private String name;
    private String postNo;
    private String addrDetail;
    @OneToMany(mappedBy = "author")
    private List<BookAuthor> bookAuthors;
}
