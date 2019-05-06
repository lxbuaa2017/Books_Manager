package daoService.book;

import entity.book.BOOK;

import java.util.Vector;

public interface GetBookByKeyword {
    Vector<BOOK> getBookByKeyword(String keyword);
}
