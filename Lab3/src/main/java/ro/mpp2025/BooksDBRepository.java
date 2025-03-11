package ro.mpp2025;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BooksDBRepository implements BookRepository {
    private JdbcUtils dbUtils;
    private static final Logger logger = LogManager.getLogger(BooksDBRepository.class);

    public BooksDBRepository(Properties props) {
        logger.info("Initializing BooksDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public void add(Book book) {
        logger.traceEntry("Saving book {}", book);
        Connection con = dbUtils.getConnection();
        try (PreparedStatement stmt = con.prepareStatement("INSERT INTO Books (title, author) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                book.setId(generatedKeys.getInt(1));
            }
            logger.info("Book saved successfully with ID {}", book.getId());
        } catch (SQLException e) {
            logger.error("Error saving book", e);
        }
    }

    @Override
    public void update(Integer id, Book book) {
        logger.traceEntry("Updating book with id {}", id);
        Connection con = dbUtils.getConnection();
        try (PreparedStatement stmt = con.prepareStatement("UPDATE Books SET title = ?, author = ? WHERE id = ?")) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                logger.warn("No book found with id {}", id);
            }
        } catch (SQLException e) {
            logger.error("Error updating book", e);
        }
    }

    @Override
    public Iterable<Book> findAll() {
        logger.traceEntry("Finding all books");
        Connection con = dbUtils.getConnection();
        List<Book> books = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM Books"); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Book book = new Book(rs.getString("title"), rs.getString("author"));
                book.setId(rs.getInt("id"));
                books.add(book);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving books", e);
        }
        return books;
    }

    @Override
    public List<Book> findByTitle(String title) {
        logger.traceEntry("Finding books by title: {}", title);
        Connection con = dbUtils.getConnection();
        List<Book> books = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM Books WHERE title = ?")) {
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Book book = new Book(rs.getString("title"), rs.getString("author"));
                book.setId(rs.getInt("id"));
                books.add(book);
            }
        } catch (SQLException e) {
            logger.error("Error finding books by title", e);
        }
        return books;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        logger.traceEntry("Finding books by author: {}", author);
        Connection con = dbUtils.getConnection();
        List<Book> books = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM Books WHERE author = ?")) {
            stmt.setString(1, author);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Book book = new Book(rs.getString("title"), rs.getString("author"));
                book.setId(rs.getInt("id"));
                books.add(book);
            }
        } catch (SQLException e) {
            logger.error("Error finding books by author", e);
        }
        return books;
    }
}

