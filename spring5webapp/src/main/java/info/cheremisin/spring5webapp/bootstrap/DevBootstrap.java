package info.cheremisin.spring5webapp.bootstrap;

import info.cheremisin.spring5webapp.model.Author;
import info.cheremisin.spring5webapp.model.Book;
import info.cheremisin.spring5webapp.model.Publisher;
import info.cheremisin.spring5webapp.repositories.AuthorRepository;
import info.cheremisin.spring5webapp.repositories.BookRepository;
import info.cheremisin.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Author jeanned = new Author("Jeanned", "Boyarsky");
        Publisher sybex = new Publisher("Sybex", "111 River Street Hoboken, NJ 07030-5774");
        Book oca = new Book("Oracle Certified Associate Java SE 8", "12323", sybex);
        Book ocp = new Book("Oracle Certified Professional Java SE 8", "12323", sybex);
        jeanned.getBooks().add(oca);
        jeanned.getBooks().add(ocp);
        oca.getAuthors().add(jeanned);
        ocp.getAuthors().add(jeanned);

        Author iuliana = new Author("Iuliana", "Cosmina");
        Publisher apress = new Publisher("Apress", "233 Spring Street, 6th Floor New York, NY 100134");
        Book spring = new Book("Pivotal Certified Spring Web Application Developer Exam", "53454", apress);
        iuliana.getBooks().add(spring);
        spring.getAuthors().add(iuliana);

        authorRepository.save(jeanned);
        authorRepository.save(iuliana);
        publisherRepository.save(sybex);
        publisherRepository.save(apress);

        bookRepository.save(oca);
        bookRepository.save(ocp);
        bookRepository.save(spring);
    }
}
