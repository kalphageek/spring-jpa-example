package me.kalpha;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import me.kalpha.book.entity.*;
import me.kalpha.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.Optional;

@Slf4j
@Component
public class TestRunner implements ApplicationRunner {
    @Autowired
    EntityManager em;
    @Autowired
    BookRepository bookRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Start >>>>>>>>>>>>>>>>>>>>");
        repoTest_CorrectResponse();
        log.info(">>>>>>>>>>>>>>>>>>>>");
        predTest_CorrectResponse();
        log.info(">>>>>>>>>>>>>>>>>>>>");
        joinTest_CorrectResponse();
        log.info("End >>>>>>>>>>>>>>>>>>>>");
    }

    public void repoTest_CorrectResponse() {
        Long id = 1L;
        QBook qBook = QBook.book;
        Predicate predicate = qBook.id.eq(id);
        Optional<Book> bookOptional = bookRepository.findOne(predicate);
        log.info("repoTest Title : {}", bookOptional.get().getTitle());
    }

    public void predTest_CorrectResponse() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        String title = "Basic JPA";
        QBook qBook = new QBook("m");
        //Predicate predicate = qBook.title.in(title);

        Book book = queryFactory.selectFrom(qBook)
                .where(qBook.title.in(title))
                .fetchFirst();
        log.info("predTest Title : {}", book.getTitle());
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

        log.info("joinTest Count : {}", query.fetchCount());
    }

    private QBean<BookQueryDto> getBookQueryProjection() {
        QBookAuthor qBookAuthor = QBookAuthor.bookAuthor;
        return Projections.bean(BookQueryDto.class, qBookAuthor.book.id, qBookAuthor.book.title, qBookAuthor.book.created, qBookAuthor.author.name.as("authorName"));
    }
}
