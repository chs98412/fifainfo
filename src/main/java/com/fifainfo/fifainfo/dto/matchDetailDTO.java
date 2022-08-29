package com.fifainfo.fifainfo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder

public class matchDetailDTO {

    Long seasonId;
    String matchResult;
    int matchEndType;
    int systemPause;
    int foul;
    int injury;
    int redCards;
    int yellowCards;
    int dribble;
    int cornerKick;
    int possession;
    int offsideCount;
    double averageRating;
    String controller;

}
