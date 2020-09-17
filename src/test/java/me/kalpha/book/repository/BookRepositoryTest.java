package me.kalpha.book.repository;

import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import me.kalpha.book.entity.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.StringUtils;

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
    Logger logger = LoggerFactory.getLogger(this.getClass());

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

    public void repoTest_CorrectResponse() {
        Long id = 1L;
        QBook qBook = QBook.book;
        Predicate predicate = qBook.id.eq(id);
        Optional<Book> bookOptional = bookRepository.findOne(predicate);
//        log.info("repoTest Title : {}", bookOptional.get().getTitle());
    }

    public void predTest_CorrectResponse() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        String title = "Basic JPA";
        QBook qBook = new QBook("m");
        //Predicate predicate = qBook.title.in(title);

        Book book = queryFactory.selectFrom(qBook)
                .where(qBook.title.in(title))
                .fetchFirst();
//        log.info("predTest Title : {}", book.getTitle());
    }

    public void joinTest_CorrectResponse() {
        String authorName = "박현정";
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QBook qBook = QBook.book;
        QAuthor qAuthor = QAuthor.author;
        QBookAuthor qBookAuthor = QBookAuthor.bookAuthor;

        JPAQuery query = queryFactory
                .select(getBookQueryProjection())
                .from(qBook)
                .join(qBookAuthor).on(qBook.id.eq(qBookAuthor.book.id))
                .join(qAuthor).on(qAuthor.id.eq(qBookAuthor.author.id));

        if (!StringUtils.isEmpty(authorName)) {
            query.where(qAuthor.name.eq(authorName));
        }

        logger.info("joinTest Count : {}", query.fetchCount());
    }

    private QBean<BookDto> getBookQueryProjection() {
        QBookAuthor qBookAuthor = QBookAuthor.bookAuthor;
        return Projections.bean(BookDto.class, qBookAuthor.book.id, qBookAuthor.book.title, qBookAuthor.book.created, qBookAuthor.author.name.as("authorName"));
    }
}