package model;

public abstract class Producer extends User {
    private String imageUrl;
    private String name;
    private int playbacks;

    public Producer(String nickName, String id, String imageUrl, String name) {
        super(nickName, id);
        this.imageUrl = imageUrl;
        this.name = name;
        playbacks=0;
    }

    public int getPlaybacks() {
        return playbacks;
    }

    public void setPlaybacks(int playbacks) {
        this.playbacks = playbacks;
    }

}
