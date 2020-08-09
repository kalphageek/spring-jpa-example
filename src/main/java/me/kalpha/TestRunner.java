package me.kalpha;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import me.kalpha.book.entity.Book;
import me.kalpha.book.entity.QBook;
import me.kalpha.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

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
}
