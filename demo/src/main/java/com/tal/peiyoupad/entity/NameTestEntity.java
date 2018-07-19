package com.tal.peiyoupad.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author SunGuiyong
 */
@Entity
@Table(name = "name_test")
public class NameTestEntity implements Serializable {

    private static final long serialVersionUID = -6083759041312261110L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer sub;
    private BigInteger bigNum;
    private BigInteger num;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime = new Date();

    public NameTestEntity() {
    }

    public NameTestEntity(String name, Integer sub) {
        this.name = name;
        this.sub = sub;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSub() {
        return sub;
    }

    public void setSub(Integer sub) {
        this.sub = sub;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public BigInteger getBigNum() {
        return bigNum;
    }

    public void setBigNum(BigInteger bigNum) {
        this.bigNum = bigNum;
    }

    public BigInteger getNum() {
        return num;
    }

    public void setNum(BigInteger num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "NameTestEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sub='" + sub + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
