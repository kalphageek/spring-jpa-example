package me.kalpha.book.repository;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import me.kalpha.book.entity.Book;
import me.kalpha.book.entity.QAuthor;
import me.kalpha.book.entity.QBook;
import me.kalpha.book.entity.QBookAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class BookQueryRepositoryCustomImpl extends QuerydslRepositorySupport implements BookQueryRepositoryCustom {
    @Autowired
    EntityManager em;

    public BookQueryRepositoryCustomImpl() {
        super(Book.class);
    }

    @Override
    public List<Book> findByAuthorName(Pageable pageable, String authorName) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QBook qBook = QBook.book;
        QAuthor qAuthor = QAuthor.author;
        QBookAuthor qBookAuthor = QBookAuthor.bookAuthor;

        JPAQuery query = queryFactory
                .selectFrom(qBook)
                .join(qBookAuthor).on(qBook.id.eq(qBookAuthor.book.id))
                .join(qAuthor).on(qAuthor.id.eq(qBookAuthor.author.id));

        if (!StringUtils.isEmpty(authorName)) {
            query.where(qAuthor.name.eq(authorName));
        }
        return query.fetch();
    }

    private ConstructorExpression<Book> getBookProjection() {
        QBook qBook = QBook.book;
        return Projections.constructor(Book.class, qBook.id, qBook.title, qBook.created);
    }
}
