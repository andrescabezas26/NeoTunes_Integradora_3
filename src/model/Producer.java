package model;

public abstract class Producer extends User{
    private String imageUrl;
    private String name;

    public Producer(String nickName, String id, String imageUrl, String name) {
        super(nickName, id);
        this.imageUrl = imageUrl;
        this.name = name;
    }

}
