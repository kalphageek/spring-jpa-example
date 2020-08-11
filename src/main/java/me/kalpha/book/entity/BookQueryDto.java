package me.kalpha.book.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class BookQueryDto {
    @Id
    private Long id;
    private String title;
    private LocalDateTime created;
    private String authorName;
}
