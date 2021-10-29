package booklibrary.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Borrow {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @OneToOne
    private Book borrowedBook;

    @ManyToOne
    @JoinColumn
    private User user;

    private Date startDate;

    private Date endDate;

    public Borrow() {}

    public Borrow(User user, Book book, Date startDate, Date endDate) {

        this.user = user;
        this.borrowedBook = book;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters

    public UUID getId() {
        return this.id;
    }

    public Book getBorrowedBook() {
        return this.borrowedBook;
    }

    public User getUser() {
        return this.user;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    // Setters

    public void setId(UUID id) {
        this.id = id;
    }

    public void setBorrowedBook(Book borrowedBook) {
        this.borrowedBook = borrowedBook;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
