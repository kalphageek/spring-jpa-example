package me.kalpha.book.projection;

import me.kalpha.book.entity.BookAuthor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

@Projection(name = "values", types = {BookAuthor.class})
public interface WithValues {
    @Value("#{target.book.id}")
    Long getBookId();
    @Value("#{target.book.title}")
    String getBookTitle();
    @Value("#{target.book.created}")
    LocalDateTime getBookCreated();
    @Value("#{target.author.name}")
    String getAuthorName();
}
