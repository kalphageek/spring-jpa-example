package me.kalpha.book.repository;

import me.kalpha.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "books", path = "books")
public interface BookRepository extends JpaRepository<Book, Long>, BookQueryRepositoryCustom, QuerydslPredicateExecutor<Book> {
}
