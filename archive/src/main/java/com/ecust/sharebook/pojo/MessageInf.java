package com.ecust.sharebook.pojo;

import java.util.Date;

public class MessageInf {
    private Integer messageId;

    private Integer senderId;

    private Integer receiverId;

    private String content;

    private Date dateTime;

    private String reason;

    private Integer borrowRes;

    private Integer mType;

    private Integer mBorrowId;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getBorrowRes() {
        return borrowRes;
    }

    public void setBorrowRes(Integer borrowRes) {
        this.borrowRes = borrowRes;
    }

    public Integer getmType() {
        return mType;
    }

    public void setmType(Integer mType) {
        this.mType = mType;
    }


    public Integer getmBorrowId() {
        return mBorrowId;
    }

    public void setmBorrowId(Integer mBorrowId) {
        this.mBorrowId = mBorrowId;
    }
}