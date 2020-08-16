package me.kalpha.library.repository;

import me.kalpha.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "lib-books", path = "lib-books")
public interface BookRepository extends JpaRepository<Book, Long>, QuerydslPredicateExecutor<Book> {
}
