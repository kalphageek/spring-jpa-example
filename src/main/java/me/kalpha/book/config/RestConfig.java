package me.kalpha.book.config;

import me.kalpha.book.entity.Author;
import me.kalpha.book.entity.Book;
import me.kalpha.book.entity.BookAuthor;
import me.kalpha.book.projection.WithValues;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration repositoryRestConfiguration) {
        repositoryRestConfiguration.getProjectionConfiguration().addProjection(WithValues.class);
//        ExposureConfiguration config = repositoryRestConfiguration.getExposureConfiguration();
//        config.forDomainType(Book.class).withItemExposure((metadata, httpMethods) -> httpMethods.disable(HttpMethod.PATCH));
//        config.forDomainType(Author.class).withItemExposure((metadata, httpMethods) -> httpMethods.disable(HttpMethod.PATCH));
//        config.forDomainType(BookAuthor.class).withItemExposure((metadata, httpMethods) -> httpMethods.disable(HttpMethod.PATCH));
    }
}
