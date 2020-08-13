package me.kalpha.book.repository;

import me.kalpha.book.entity.BookAuthor;
import me.kalpha.book.projection.WithValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = WithValues.class, collectionResourceRel = "bookAuthors", path = "bookAuthors")
public interface BookAuthorAuthorRepository extends JpaRepository<BookAuthor, Long>, BookAuthorRepositoryCustom {
}
