package me.kalpha.book.repository;

import me.kalpha.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookQueryRepositoryCustom {
    Page<Book> findByAuthorName(Pageable pageable, String authorName);
}
