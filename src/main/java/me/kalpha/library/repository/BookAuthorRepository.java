package me.kalpha.library.repository;

import me.kalpha.entity.BookAuthor;
import me.kalpha.book.projection.BookAuthorValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = BookAuthorValues.class, collectionResourceRel = "lib-bookAuthors", path = "lib-bookAuthors")
public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long>, BookAuthorRepositoryCustom {
}
