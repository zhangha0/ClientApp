package com.cd.wzjkj.canyi.entity;

import java.io.Serializable;

/**
 * Created by liuzheng on 2016/11/30.
 */
public class SelfComment implements Serializable {
    private String comment_icon = "http://img5.imgtn.bdimg.com/it/u=870609829,3308433796&fm=23&gp=0.jpg";
    private String title = "评论标题";
    private String data="2016.12.06";
    private String content_comment = "评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论";

    public String getContent_comment() {
        return content_comment;
    }

    public void setContent_comment(String content_comment) {
        this.content_comment = content_comment;
    }

    public String getComment_icon() {
        return comment_icon;
    }

    public void setComment_icon(String comment_icon) {
        this.comment_icon = comment_icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
