package me.kalpha.library.repository;

import me.kalpha.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "lib-authors", path = "lib-authors")
public interface AuthorRepository extends JpaRepository<Author, Long>  {
}
