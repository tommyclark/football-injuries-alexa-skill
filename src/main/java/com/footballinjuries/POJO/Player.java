package com.footballinjuries.POJO;

import lombok.Data;

import java.util.List;

@Data
public class Player {
    private Footballer player;
    private List<PlayerStatistics> statistics;
}
