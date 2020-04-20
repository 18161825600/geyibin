package com.example.geyibin.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_user")
public class User {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    private String password;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "pay_password")
    private String payPassword;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "last_money")
    private Double lastMoney;

    /**
     * 是否交押金了，0是没有，1是交了
     */
    @Column(name = "is_deposit")
    private Short isDeposit;

    /**
     * 押金剩余金额
     */
    @Column(name = "last_deposit")
    private Double lastDeposit;

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
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return nick_name
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * @return phone_number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     * @return pay_password
     */
    public String getPayPassword() {
        return payPassword;
    }

    /**
     * @param payPassword
     */
    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword == null ? null : payPassword.trim();
    }

    /**
     * @return img_url
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * @return last_money
     */
    public Double getLastMoney() {
        return lastMoney;
    }

    /**
     * @param lastMoney
     */
    public void setLastMoney(Double lastMoney) {
        this.lastMoney = lastMoney;
    }

    /**
     * 获取是否交押金了，0是没有，1是交了
     *
     * @return is_deposit - 是否交押金了，0是没有，1是交了
     */
    public Short getIsDeposit() {
        return isDeposit;
    }

    /**
     * 设置是否交押金了，0是没有，1是交了
     *
     * @param isDeposit 是否交押金了，0是没有，1是交了
     */
    public void setIsDeposit(Short isDeposit) {
        this.isDeposit = isDeposit;
    }

    /**
     * 获取押金剩余金额
     *
     * @return last_deposit - 押金剩余金额
     */
    public Double getLastDeposit() {
        return lastDeposit;
    }

    /**
     * 设置押金剩余金额
     *
     * @param lastDeposit 押金剩余金额
     */
    public void setLastDeposit(Double lastDeposit) {
        this.lastDeposit = lastDeposit;
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