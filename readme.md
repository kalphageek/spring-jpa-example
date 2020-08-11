## 관계 엔티티(BOOK_AUTHOR)에 Insert
> [POST] http://localhost:8080/api/bookAuthors
```json
{
	"book": "http://localhost:8080/api/books/1",
	"author": "http://localhost:8080/api/authors/5",
	"seq":"1"
}
```
## 조인테이블 컬럼값으로 filter
> [GET] http://localhost:8080/api/books/search/findByAuthorName?authorName=정진덕