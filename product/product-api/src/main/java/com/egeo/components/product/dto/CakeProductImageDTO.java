package com.egeo.components.product.dto;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/4/28 22:49
 * @Version V1.0
 **/
public class CakeProductImageDTO implements Serializable {

    /**
     * string	1252538	banner轮播图片id或图文详情图片id
     **/
    private String id;

    /**
     * string banner轮播大图地址或图文详情大图地址 https://img.dangaoss.com/public/p/89/14/1252538_l.jpg
     **/
    private String m_path;

    /**
     * string	640	banner轮播大图宽度或图文详情大图宽度
     **/
    private String m_width;
    /**
     * string	640	banner轮播大图高度或图文详情大图高度
     **/
    private String m_height;
    /**
     * string		banner轮播中图地址或图文详情中图地址 https://img.dangaoss.com/public/m/89/14/1252538_l.jpg
     **/
    private String l_path;
    /**
     * 	string	320	banner轮播中图宽度或图文详情中图宽度
     **/
    private String l_width;
    /**
     * 	string	320	banner轮播中图高度或图文详情中图高度
     **/
    private String l_height;
    /**
     * string banner轮播小图地址或图文详情小图地址 https://img.dangaoss.com/public/p/90/15/1252539_s.jpg
     **/
    private String s_path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getM_path() {
        return m_path;
    }

    public void setM_path(String m_path) {
        this.m_path = m_path;
    }

    public String getM_width() {
        return m_width;
    }

    public void setM_width(String m_width) {
        this.m_width = m_width;
    }

    public String getM_height() {
        return m_height;
    }

    public void setM_height(String m_height) {
        this.m_height = m_height;
    }

    public String getL_path() {
        return l_path;
    }

    public void setL_path(String l_path) {
        this.l_path = l_path;
    }

    public String getL_width() {
        return l_width;
    }

    public void setL_width(String l_width) {
        this.l_width = l_width;
    }

    public String getL_height() {
        return l_height;
    }

    public void setL_height(String l_height) {
        this.l_height = l_height;
    }

    public String getS_path() {
        return s_path;
    }

    public void setS_path(String s_path) {
        this.s_path = s_path;
    }
}
