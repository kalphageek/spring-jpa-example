package me.kalpha.library.repository;

import me.kalpha.entity.BookAuthor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface BookAuthorRepositoryCustom {
    Page<BookAuthor> findAllByAuthorName(Pageable pageable, @Param("authorName") String authorName);
}
