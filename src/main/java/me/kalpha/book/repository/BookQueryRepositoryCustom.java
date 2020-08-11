package me.kalpha.book.repository;

import me.kalpha.book.entity.BookQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookQueryRepositoryCustom {
    Page<BookQueryDto> findByAuthorName(Pageable pageable, String authorName);
}
