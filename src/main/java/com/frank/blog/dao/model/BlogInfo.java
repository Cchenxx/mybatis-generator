package com.frank.blog.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "blog_info")
public class BlogInfo implements Serializable {
    /**
     * 博客id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 用户头像
     */
    @Column(name = "pic_url")
    private String picUrl;

    /**
     * 状态：0-不可用;1-可用
     */
    private Integer status;

    /**
     * 评论数
     */
    @Column(name = "reply_count")
    private Integer replyCount;

    /**
     * 点赞数
     */
    @Column(name = "like_count")
    private Integer likeCount;

    /**
     * 创造时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 0:不是转发 1:是转发
     */
    @Column(name = "is_forw")
    private Integer isForw;

    /**
     * 0:不可以转发 1:可以转发
     */
    @Column(name = "can_forw")
    private Integer canForw;

    /**
     * 转发人
     */
    @Column(name = "forw_user_id")
    private Integer forwUserId;

    /**
     * 转发的博客id
     */
    @Column(name = "forw_blog_info_id")
    private Integer forwBlogInfoId;

    /**
     * 内容
     */
    private String content;

    private static final long serialVersionUID = 1L;

    /**
     * 获取博客id
     *
     * @return id - 博客id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置博客id
     *
     * @param id 博客id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取用户昵称
     *
     * @return nick_name - 用户昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置用户昵称
     *
     * @param nickName 用户昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * 获取用户头像
     *
     * @return pic_url - 用户头像
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 设置用户头像
     *
     * @param picUrl 用户头像
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    /**
     * 获取状态：0-不可用;1-可用
     *
     * @return status - 状态：0-不可用;1-可用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0-不可用;1-可用
     *
     * @param status 状态：0-不可用;1-可用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取评论数
     *
     * @return reply_count - 评论数
     */
    public Integer getReplyCount() {
        return replyCount;
    }

    /**
     * 设置评论数
     *
     * @param replyCount 评论数
     */
    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    /**
     * 获取点赞数
     *
     * @return like_count - 点赞数
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * 设置点赞数
     *
     * @param likeCount 点赞数
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * 获取创造时间
     *
     * @return create_time - 创造时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创造时间
     *
     * @param createTime 创造时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取0:不是转发 1:是转发
     *
     * @return is_forw - 0:不是转发 1:是转发
     */
    public Integer getIsForw() {
        return isForw;
    }

    /**
     * 设置0:不是转发 1:是转发
     *
     * @param isForw 0:不是转发 1:是转发
     */
    public void setIsForw(Integer isForw) {
        this.isForw = isForw;
    }

    /**
     * 获取0:不可以转发 1:可以转发
     *
     * @return can_forw - 0:不可以转发 1:可以转发
     */
    public Integer getCanForw() {
        return canForw;
    }

    /**
     * 设置0:不可以转发 1:可以转发
     *
     * @param canForw 0:不可以转发 1:可以转发
     */
    public void setCanForw(Integer canForw) {
        this.canForw = canForw;
    }

    /**
     * 获取转发人
     *
     * @return forw_user_id - 转发人
     */
    public Integer getForwUserId() {
        return forwUserId;
    }

    /**
     * 设置转发人
     *
     * @param forwUserId 转发人
     */
    public void setForwUserId(Integer forwUserId) {
        this.forwUserId = forwUserId;
    }

    /**
     * 获取转发的博客id
     *
     * @return forw_blog_info_id - 转发的博客id
     */
    public Integer getForwBlogInfoId() {
        return forwBlogInfoId;
    }

    /**
     * 设置转发的博客id
     *
     * @param forwBlogInfoId 转发的博客id
     */
    public void setForwBlogInfoId(Integer forwBlogInfoId) {
        this.forwBlogInfoId = forwBlogInfoId;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BlogInfo other = (BlogInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
            && (this.getPicUrl() == null ? other.getPicUrl() == null : this.getPicUrl().equals(other.getPicUrl()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getReplyCount() == null ? other.getReplyCount() == null : this.getReplyCount().equals(other.getReplyCount()))
            && (this.getLikeCount() == null ? other.getLikeCount() == null : this.getLikeCount().equals(other.getLikeCount()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsForw() == null ? other.getIsForw() == null : this.getIsForw().equals(other.getIsForw()))
            && (this.getCanForw() == null ? other.getCanForw() == null : this.getCanForw().equals(other.getCanForw()))
            && (this.getForwUserId() == null ? other.getForwUserId() == null : this.getForwUserId().equals(other.getForwUserId()))
            && (this.getForwBlogInfoId() == null ? other.getForwBlogInfoId() == null : this.getForwBlogInfoId().equals(other.getForwBlogInfoId()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getPicUrl() == null) ? 0 : getPicUrl().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getReplyCount() == null) ? 0 : getReplyCount().hashCode());
        result = prime * result + ((getLikeCount() == null) ? 0 : getLikeCount().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsForw() == null) ? 0 : getIsForw().hashCode());
        result = prime * result + ((getCanForw() == null) ? 0 : getCanForw().hashCode());
        result = prime * result + ((getForwUserId() == null) ? 0 : getForwUserId().hashCode());
        result = prime * result + ((getForwBlogInfoId() == null) ? 0 : getForwBlogInfoId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", userId=").append(userId);
        sb.append(", nickName=").append(nickName);
        sb.append(", picUrl=").append(picUrl);
        sb.append(", status=").append(status);
        sb.append(", replyCount=").append(replyCount);
        sb.append(", likeCount=").append(likeCount);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isForw=").append(isForw);
        sb.append(", canForw=").append(canForw);
        sb.append(", forwUserId=").append(forwUserId);
        sb.append(", forwBlogInfoId=").append(forwBlogInfoId);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}