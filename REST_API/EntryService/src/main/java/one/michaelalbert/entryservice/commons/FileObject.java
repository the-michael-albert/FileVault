package one.michaelalbert.entryservice.commons;

import java.util.Random;

public class FileObject {

    private String name;
    private int filetype;
    private final String resourceID = genResID();

    private int width, height, duration;
    private int year, month, date;

    public FileObject(String name, int filetype, int width, int height, int duration, int year, int month, int date) {
        this.name = name;
        this.filetype = filetype;
        this.width = width;
        this.height = height;
        this.duration = duration;
        this.year = year;
        this.month = month;
        this.date = date;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFiletype() {
        return filetype;
    }

    public void setFiletype(int filetype) {
        this.filetype = filetype;
    }

    public String getResourceID() {
        return resourceID;
    }

    public static String genResID(){
        String out = "";
        String alpha = "abcdefghijklmnopqrstuvwzyx";
        for (int i = 0; i < 40; i++) {
            out += alpha.charAt(new Random().nextInt(alpha.length()));
        }
        return out;
    }
}
