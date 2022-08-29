package com.fifainfo.fifainfo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder

public class gameresultDTO {


    String matchId;
    String matchDate;
    String matchType;

    List<matchInfoDTO> matchInfo = new ArrayList<matchInfoDTO>();

}
