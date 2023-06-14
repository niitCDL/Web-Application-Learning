package com.example.book.entity;

public class Channel {
    private String name;
    private String code;

    public Channel(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
