package daoService.book;

import entity.book.BOOK;

import java.util.Vector;

public interface SerchBookByKeyWord {
    public Vector<BOOK> serchBookByKeyWord(String str);
}
