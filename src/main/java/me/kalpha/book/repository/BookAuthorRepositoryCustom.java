package me.kalpha.book.repository;

import me.kalpha.book.entity.BookAuthor;
import me.kalpha.book.entity.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface BookAuthorRepositoryCustom {
    Page<BookAuthor> findAllByAuthorName(Pageable pageable, @Param("authorName") String authorName);
}
