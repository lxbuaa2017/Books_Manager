package daoService.book;

import entity.book.BOOK;

public interface UpdateBooks {
    boolean updateBook(String isbn, BOOK book);
}
