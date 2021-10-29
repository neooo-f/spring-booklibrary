package booklibrary.entities;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
public class Book {

    @Id @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        @Type(type = "org.hibernate.type.UUIDCharType")
        @Column(updatable = false, nullable = false)
        private UUID id;
    private String title;
    private String author;
    private int releaseYear;

    public Book() {}

    Book(String title, String author, int releaseYear) {

        this.title = title;
        this.author = author;
        this.releaseYear = releaseYear;
    }

    // Getters

    public UUID getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    // Setters

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Book))
            return false;
        Book book = (Book) o;
        return Objects.equals(this.id, book.id) && Objects.equals(this.title, book.title)
                && Objects.equals(this.author, book.author) && Objects.equals(this.releaseYear, book.releaseYear);
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + this.id + ", title='" + this.title + '\'' + ", author='" + this.author + '\'' + ", release year='" + this.releaseYear + '\'' + '}';
    }

}
