package com.example.geyibin.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_borrow_book")
public class BorrowBook {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "user_id")
    private Long userId;

    /**
     * 0期限内未还1续借2期限内归还3逾期归还4逾期未归还
     */
    private Short state;

    /**
     * 借书时长
     */
    private Integer duration;

    /**
     * 到期时间
     */
    @Column(name = "due_time")
    private Date dueTime;

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
     * @return book_id
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * @param bookId
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取0期限内未还1续借2期限内归还3逾期归还4逾期未归还
     *
     * @return state - 0期限内未还1续借2期限内归还3逾期归还4逾期未归还
     */
    public Short getState() {
        return state;
    }

    /**
     * 设置0期限内未还1续借2期限内归还3逾期归还4逾期未归还
     *
     * @param state 0期限内未还1续借2期限内归还3逾期归还4逾期未归还
     */
    public void setState(Short state) {
        this.state = state;
    }

    /**
     * 获取借书时长
     *
     * @return duration - 借书时长
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * 设置借书时长
     *
     * @param duration 借书时长
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * 获取到期时间
     *
     * @return due_time - 到期时间
     */
    public Date getDueTime() {
        return dueTime;
    }

    /**
     * 设置到期时间
     *
     * @param dueTime 到期时间
     */
    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
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