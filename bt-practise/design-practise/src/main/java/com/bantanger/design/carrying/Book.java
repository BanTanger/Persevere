package com.bantanger.design.carrying;

import java.time.LocalDate;
import java.util.function.Function;

/**
 * @author chensongmin
 * @description
 * @date 2025/3/8
 */
public class Book {

    private final Genre genre;
    private final String author;
    private final String title;
    private final LocalDate publicationDate;

    Book(Genre genre, String author, String title, LocalDate publicationDate) {
        this.genre = genre;
        this.author = author;
        this.title = title;
        this.publicationDate = publicationDate;
    }

    // 简单方法创建 Book 对象
    public static Book of(Genre genre, String author, String title, LocalDate publicationDate) {
        return new Book(genre, author, title, publicationDate);
    }

    // carrying 柯里化创建 精确 book 对象
    public static Function<Genre, Function<String, Function<String, Function<LocalDate, Book>>>> bookCreator
        = bookGenre
        -> bookAuthor
        -> bookTitle
        -> bookPublicationDate
        -> new Book(bookGenre, bookAuthor, bookTitle, bookPublicationDate);

    // 或者这样，进一步封装
    public static Function<String, Function<String, Function<LocalDate, Book>>> fantasyBookFunc = Book.bookCreator.apply(Genre.FANTASY);

    // 构造器模式 + 函数式接口优化过长的类型签名
    public static AddGenre builder() {
        return genre -> author -> title -> publicationDate -> new Book(genre, author, title, publicationDate);
    }

    @FunctionalInterface
    public interface AddGenre {
        Book.AddAuthor withGenre(Genre genre);
    }

    @FunctionalInterface
    public interface AddAuthor {
        Book.AddTitle withAuthor(String author);
    }

    @FunctionalInterface
    public interface AddTitle {
        Book.AddPublicationDate withTitle(String title);
    }

    @FunctionalInterface
    public interface AddPublicationDate {
        Book buildWithPublicationDate(LocalDate publicationDate);
    }

}
