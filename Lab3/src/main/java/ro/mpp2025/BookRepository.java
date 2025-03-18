package ro.mpp2025;

import java.util.List;

public interface BookRepository extends Repository<Integer,Book> {
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
}