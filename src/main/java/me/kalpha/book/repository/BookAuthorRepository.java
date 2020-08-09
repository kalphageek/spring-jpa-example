package me.kalpha.book.repository;

import me.kalpha.book.entity.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "bookAuthors", path = "bookAuthors")
public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long> {
}
