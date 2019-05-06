package daoService.book;

import entity.book.BOOK;
import entity.book.BOOKException;


public interface AddBooks{
    boolean addBooks(BOOK book) throws BOOKException;
}
