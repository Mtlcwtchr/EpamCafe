package by.epam.mtlcwtchr.ecafe.entity;

import java.io.Serializable;
import java.util.Objects;

public class Comment implements Serializable {

    private static final long serialVersionUID = 9067919397164539704L;

    private int id;
    private String authorName;
    private String authorPhone;
    private String message;

    private int authorId;

    public Comment(){}
    public Comment(String authorName, String authorPhone, String message, int authorId) {
        this.message = message;
        this.authorName = authorName;
        this.authorPhone = authorPhone;
        this.authorId = authorId;
    }
    public Comment(int id, String authorName, String authorPhone, String message, int authorId) {
        this(message, authorName, authorPhone, authorId);
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public String getAuthorPhone() {
        return authorPhone;
    }
    public void setAuthorPhone(String authorPhone) {
        this.authorPhone = authorPhone;
    }
    public int getAuthorId() {
        return authorId;
    }
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id &&
                authorId == comment.authorId &&
                Objects.equals(message, comment.message) &&
                Objects.equals(authorName, comment.authorName) &&
                Objects.equals(authorPhone, comment.authorPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, authorName, authorPhone, authorId);
    }

    @Override
    public String toString() {
        return  getClass().getSimpleName() + "{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorPhone='" + authorPhone + '\'' +
                ", authorId=" + authorId +
                '}';
    }

}
