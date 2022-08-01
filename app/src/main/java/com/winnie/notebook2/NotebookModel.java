package com.winnie.notebook2;

import java.io.Serializable;

public class NotebookModel  implements Serializable {

    String  content;
    String category;
    long datetime;

    public NotebookModel() {
    }

    public NotebookModel(String content, String category, long datetime) {
        this.content = content;
        this.category = category;
        this.datetime = datetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }
}
