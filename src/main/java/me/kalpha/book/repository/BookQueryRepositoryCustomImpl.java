package me.kalpha.book.repository;

import com.querydsl.core.types.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import me.kalpha.book.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;

public class BookQueryRepositoryCustomImpl extends QuerydslRepositorySupport implements BookQueryRepositoryCustom {
    @Autowired
    EntityManager em;

    public BookQueryRepositoryCustomImpl() {
        super(Book.class);
    }

    @Override
    public Page<BookQueryDto> findAllByAuthorName(Pageable pageable, String authorName) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QBook qBook = QBook.book;
        QAuthor qAuthor = QAuthor.author;
        QBookAuthor qBookAuthor = QBookAuthor.bookAuthor;

        JPAQuery query = queryFactory
                .from(qBook)
                .select(getBookQueryProjection())
                .join(qBookAuthor).on(qBook.id.eq(qBookAuthor.book.id))
                .join(qAuthor).on(qAuthor.id.eq(qBookAuthor.author.id));

        if (!StringUtils.isEmpty(authorName)) {
            query.where(qAuthor.name.eq(authorName));
        }

        return new PageImpl<BookQueryDto>(query.fetchResults().getResults(),
                pageable,
                query.fetchResults().getTotal());
    }

    private QBean<BookQueryDto> getBookQueryProjection() {
        QBookAuthor qBookAuthor = QBookAuthor.bookAuthor;
        return Projections.bean(BookQueryDto.class, qBookAuthor.book.id, qBookAuthor.book.title, qBookAuthor.book.created, qBookAuthor.author.name.as("authorName"));
    }
}
