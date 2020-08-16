package me.kalpha.config;

import me.kalpha.entity.Author;
import me.kalpha.entity.Book;
import me.kalpha.entity.BookAuthor;
import me.kalpha.book.projection.BookAuthorValues;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration repositoryRestConfiguration) {
        repositoryRestConfiguration.getProjectionConfiguration().addProjection(BookAuthorValues.class);
        ExposureConfiguration config = repositoryRestConfiguration.getExposureConfiguration();
        config.forDomainType(Book.class).withItemExposure((metadata, httpMethods) -> httpMethods.disable(HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PUT));
        config.forDomainType(Author.class).withItemExposure((metadata, httpMethods) -> httpMethods.disable(HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PUT));
        config.forDomainType(BookAuthor.class).withItemExposure((metadata, httpMethods) -> httpMethods.disable(HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PUT));
    }
}
