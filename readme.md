## 관계 엔티티(BOOK_AUTHOR)에 Insert
> [POST] http://localhost:8080/api/bookAuthors
```json
{
	"book": "http://localhost:8080/api/books/1",
	"author": "http://localhost:8080/api/authors/5",
	"seq":"1"
}