package com.zhou.bean;

import java.io.Serializable;
import java.util.Date;

public class ThingRecordTo implements Serializable {
    private static final long serialVersionUID = -5747593142115680742L;
    private Integer id;

    private Integer manUserId;

    private Integer womanUserId;

    private Date happedTime;

    private String happedPlace;

    private String introDes;

    private Byte type;

    private String img;

    private String summary;

    private Date createdTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getManUserId() {
        return manUserId;
    }

    public void setManUserId(Integer manUserId) {
        this.manUserId = manUserId;
    }

    public Integer getWomanUserId() {
        return womanUserId;
    }

    public void setWomanUserId(Integer womanUserId) {
        this.womanUserId = womanUserId;
    }

    public Date getHappedTime() {
        return happedTime;
    }

    public void setHappedTime(Date happedTime) {
        this.happedTime = happedTime;
    }

    public String getHappedPlace() {
        return happedPlace;
    }

    public void setHappedPlace(String happedPlace) {
        this.happedPlace = happedPlace;
    }

    public String getIntroDes() {
        return introDes;
    }

    public void setIntroDes(String introDes) {
        this.introDes = introDes;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}