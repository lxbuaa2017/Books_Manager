package entity;

import tool.Isbn;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class BOOK {             // Book在jdk8中有定义，考虑到重名会有不便所以不用
    private String ISBN;        //tool.Isbn
    private String title;       //书名
    private String[] author;    //作者
    private BigDecimal price;   //价格
    private int inventory;  //库存
    private int totalNum;   //上限总量

    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(String ISBNstr) {
        ISBNstr = ISBNstr.replace(" ", "").replace("-", "");
        while(!Isbn.checkIsbn(ISBNstr))
            try {
                throw new Exception();
            }
            catch (Exception e){
                System.out.println("ISBN不合法！请输入合法值：");
                Scanner sc=new Scanner(System.in);
                ISBNstr=sc.nextLine().replace(" ", "").replace("-", "");
                sc.close();
            }
        this.ISBN=ISBNstr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title.isEmpty())
            title="无";
        this.title = title;
    }

    public String[] getAuthor() {
        return author;
    }

    public void setAuthor(String[] author) {
        if(author.length==0)
            author=new String[]{"无"};
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(String price) {
        while (Double.parseDouble(price)<0)
            try{
                throw new Exception();
            }
            catch(Exception e){
                System.out.println("价格不能小于0,请重新输入：");
                Scanner sc=new Scanner(System.in);
                System.out.println("price:");price=sc.nextLine();
                sc.close();
            }
        this.price = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP); //保留两位小数（到分），四舍五入
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        while (inventory<0)
            try{
                throw new Exception();
            }
            catch(Exception e){
                System.out.println("库存不能小于0,请重新输入：");
                Scanner sc=new Scanner(System.in);
                System.out.println("inventory:");inventory=sc.nextInt();
                sc.close();
            }
        this.inventory = inventory;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        while (totalNum<0)
            try{
                throw new Exception();
            }
            catch(Exception e){
                System.out.println("库存上限不能小于0,请重新输入：");
                Scanner sc=new Scanner(System.in);
                System.out.println("totalNum:");totalNum=sc.nextInt();
                sc.close();
            }
        this.totalNum = totalNum;
    }

    @Override
    public String toString() {
        return "BOOK{" +
                "tool.Isbn='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ", author=" + Arrays.toString(author) +
                ", price=" + price +
                ", inventory=" + inventory +
                ", totalNum=" + totalNum +
                '}';
    }

    public BOOK(String ISBNstr, String title, String[] author, String price) {
        this.setISBN(ISBNstr);
        this.setTitle(title);
        this.setAuthor(author);
        this.setPrice(price);
        this.setInventory(0);
        this.setTotalNum(0);
    }

    public void print(){
        System.out.println(this.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BOOK book = (BOOK) o;
        return this.ISBN.equals(book.ISBN);  //String的equals比较内容
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN);
    }
}
