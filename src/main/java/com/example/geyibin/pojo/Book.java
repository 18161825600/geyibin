package com.example.geyibin.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_book")
public class Book {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name")
    private String bookName;

    /**
     * 封面图片
     */
    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "author_name")
    private String authorName;

    /**
     * 剩余图书数量
     */
    @Column(name = "last_number")
    private Integer lastNumber;

    @Column(name = "book_price")
    private Double bookPrice;

    @Column(name = "book_content")
    private String bookContent;

    @Column(name = "author_content")
    private String authorContent;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return Id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return book_name
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * @param bookName
     */
    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    /**
     * 获取封面图片
     *
     * @return cover_url - 封面图片
     */
    public String getCoverUrl() {
        return coverUrl;
    }

    /**
     * 设置封面图片
     *
     * @param coverUrl 封面图片
     */
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl == null ? null : coverUrl.trim();
    }

    /**
     * @return author_name
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * @param authorName
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName == null ? null : authorName.trim();
    }

    /**
     * 获取剩余图书数量
     *
     * @return last_number - 剩余图书数量
     */
    public Integer getLastNumber() {
        return lastNumber;
    }

    /**
     * 设置剩余图书数量
     *
     * @param lastNumber 剩余图书数量
     */
    public void setLastNumber(Integer lastNumber) {
        this.lastNumber = lastNumber;
    }

    /**
     * @return book_price
     */
    public Double getBookPrice() {
        return bookPrice;
    }

    /**
     * @param bookPrice
     */
    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    /**
     * @return book_content
     */
    public String getBookContent() {
        return bookContent;
    }

    /**
     * @param bookContent
     */
    public void setBookContent(String bookContent) {
        this.bookContent = bookContent == null ? null : bookContent.trim();
    }

    /**
     * @return author_content
     */
    public String getAuthorContent() {
        return authorContent;
    }

    /**
     * @param authorContent
     */
    public void setAuthorContent(String authorContent) {
        this.authorContent = authorContent == null ? null : authorContent.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}