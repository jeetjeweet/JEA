package model;

public class Message {
    private String content;
    public String getContent() {
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }
    public Message(String content) {
        setContent(content);
    }
}
