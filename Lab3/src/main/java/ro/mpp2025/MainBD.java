package ro.mpp2025;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MainBD {
    public static void main(String[] args) {

        Properties props=new Properties();
        try {
            props.load(new FileReader("C:\\Users\\Alexandra\\Desktop\\Facultate anul 2\\Semestrul 2\\MPP\\Laborator\\teme-lab-elaa10\\Lab3\\bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }

        BookRepository bookRepo=new BooksDBRepository(props);

        bookRepo.add(new Book("Strainul", "Albert Camus"));
        System.out.println("Toate cartile din db");
        for(Book book:bookRepo.findAll())
            System.out.println(book);
        String author = "Radu Tudoran";
        System.out.println("Cartile scrise de "  + author);
        for(Book book:bookRepo.findByAuthor(author))
            System.out.println(book);

        bookRepo.update(2,new Book("Toate panzele jos!", "Radu Tudoran"));

        System.out.println("Cartile scrise de "  + author);
        for(Book book:bookRepo.findByAuthor(author))
            System.out.println(book);

    }
}
