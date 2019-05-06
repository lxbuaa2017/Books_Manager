package daoService.book;

import entity.book.BOOKException;

public interface DeleteBooks {
    boolean deleteBooks(String isbn) throws BOOKException;
}
