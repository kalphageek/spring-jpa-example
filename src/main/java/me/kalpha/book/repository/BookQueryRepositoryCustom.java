package me.kalpha.book.repository;

import me.kalpha.book.entity.BookQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface BookQueryRepositoryCustom {
    Page<BookQueryDto> findAllByAuthorName(Pageable pageable, @Param("authorName") String authorName);
}
