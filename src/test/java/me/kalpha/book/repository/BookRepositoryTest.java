package me.kalpha.book.repository;

import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import me.kalpha.book.entity.Book;
import me.kalpha.book.entity.QBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    EntityManager em;

    @Test
    public void whenFindOne_CorrectResponse() {
        Long id = 1L;
        QBook qBook = QBook.book;
        Predicate predicate = qBook.id.eq(id);
        Optional<Book> bookOptional = bookRepository.findOne(predicate);
        assertTrue(bookOptional.isPresent());
    }

    @Test
    public void whenFindOne2_CorrectResponse() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        String title = "Basic JPA";
        QBook qBook = new QBook("m");
        //Predicate predicate = qBook.title.in(title);

        Book book = queryFactory.selectFrom(qBook)
                .where(qBook.title.in(title))
                .fetchFirst();

        assertNotNull(book);

    }

}