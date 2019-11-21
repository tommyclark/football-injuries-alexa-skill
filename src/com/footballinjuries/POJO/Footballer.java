package com.footballinjuries.POJO;

import lombok.Data;

import java.util.List;

@Data
public class Footballer {
    private int id;

    private String name;

    private String firstName;

    private String lastName;

    private boolean injured;

    private List<PlayerStatistics> statistics;
}
