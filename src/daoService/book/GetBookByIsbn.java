package daoService.book;

import entity.book.BOOK;
import tool.Isbn;

public interface GetBookByIsbn {
    BOOK getBookByIsbn(Isbn isbn);
}
