import java.util.*;

class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getBookId() {
        return bookId;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issue() {
        isIssued = true;
    }

    public void returned() {
        isIssued = false;
    }

    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Issued: " + isIssued;
    }
}

class User {
    private int userId;
    private String name;
    private List<Book> borrowedBooks;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.issue();
        System.out.println(name + " borrowed: " + book.toString());
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.returned();
            System.out.println(name + " returned: " + book.toString());
        } else {
            System.out.println("Book not found in user's borrowed list.");
        }
    }

    public String toString() {
        return "User ID: " + userId + ", Name: " + name + ", Borrowed Books: " + borrowedBooks.size();
    }
}

class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void displayBooks() {
        System.out.println("\nAvailable Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void displayUsers() {
        System.out.println("\nRegistered Users:");
        for (User user : users) {
            System.out.println(user);
        }
    }

    public Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) return book;
        }
        return null;
    }

    public User findUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) return user;
        }
        return null;
    }

    public void issueBookToUser(int userId, int bookId) {
        Book book = findBookById(bookId);
        User user = findUserById(userId);

        if (book == null || user == null) {
            System.out.println("Book or User not found.");
            return;
        }

        if (book.isIssued()) {
            System.out.println("Book is already issued.");
        } else {
            user.borrowBook(book);
        }
    }

    public void returnBookFromUser(int userId, int bookId) {
        Book book = findBookById(bookId);
        User user = findUserById(userId);

        if (book == null || user == null) {
            System.out.println("Book or User not found.");
            return;
        }

        user.returnBook(book);
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Add sample books
        library.addBook(new Book(1, "Java Basics", "James Gosling"));
        library.addBook(new Book(2, "Effective Java", "Joshua Bloch"));

        // Add sample users
        library.addUser(new User(100, "Alice"));
        library.addUser(new User(101, "Bob"));

        library.displayBooks();
        library.displayUsers();

        // Issue and return operations
        library.issueBookToUser(100, 1);
        library.issueBookToUser(101, 2);

        library.displayBooks();

        library.returnBookFromUser(100, 1);

        library.displayBooks();
    }
}
