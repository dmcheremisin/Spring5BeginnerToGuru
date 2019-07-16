package info.cheremisin.spring5webapp.bootstrap;

import info.cheremisin.spring5webapp.model.Author;
import info.cheremisin.spring5webapp.model.Book;
import info.cheremisin.spring5webapp.repositories.AuthorRepository;
import info.cheremisin.spring5webapp.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Author jeanned = new Author("Jeanned", "Boyarsky");
        Book oca = new Book("Oracle Certified Associate Java SE 8", "12323", "Sybex");
        Book ocp = new Book("Oracle Certified Professional Java SE 8", "12323", "Sybex");
        jeanned.getBooks().add(oca);
        jeanned.getBooks().add(ocp);
        oca.getAuthors().add(jeanned);
        ocp.getAuthors().add(jeanned);

        Author iuliana = new Author("Iuliana", "Cosmina");
        Book spring = new Book("Pivotal Certified Spring Web Application Developer Exam", "53454", "Apress");
        iuliana.getBooks().add(spring);
        spring.getAuthors().add(iuliana);

        authorRepository.save(jeanned);
        authorRepository.save(iuliana);

        bookRepository.save(oca);
        bookRepository.save(ocp);
        bookRepository.save(spring);
    }
}
