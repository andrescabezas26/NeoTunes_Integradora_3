package model;

public class Audio {
    private String creator;
    private String name;
    private String url;
    private int hours;
    private int minutes;
    private int seconds;
    private int playbacks;
    private int duration;

    public Audio(String creator, String name, String url, int hours, int minutes, int seconds) {
        this.creator = creator;
        this.name = name;
        this.url = url;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.playbacks = 0;
        this.duration= (hours*3600)+(minutes*60)+seconds;
    }

    public String getCreator() {
        return creator;
    }

    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getPlaybacks() {
        return playbacks;
    }

    public int getDuration() {
        return duration;
    }

    public void setPlaybacks(int playbacks) {
        this.playbacks = playbacks;
    }

    @Override
    public String toString() {
        return "Duracion: "+ hours+":"+minutes+":"+seconds+"\nNombre: "+name+"\nProductor: "+creator;
    }

}
