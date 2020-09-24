package com.zhou.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 协议类
 */
public class LoveAgreeTo implements Serializable{
    private static final long serialVersionUID = -2265145268748758755L;
    private Integer id;

    private Integer manUserId;

    private Integer womanUserId;

    private String agreeIntro;

    private String punish;

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

    public String getAgreeIntro() {
        return agreeIntro;
    }

    public void setAgreeIntro(String agreeIntro) {
        this.agreeIntro = agreeIntro;
    }

    public String getPunish() {
        return punish;
    }

    public void setPunish(String punish) {
        this.punish = punish;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}