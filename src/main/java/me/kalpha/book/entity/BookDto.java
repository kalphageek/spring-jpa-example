package me.kalpha.book.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
public class BookDto {
    @Id
    private Long id;
    private String title;
    private LocalDateTime created;
    private String authorName;
}
