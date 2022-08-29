package com.fifainfo.fifainfo.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder

public class matchInfoDTO {


    String nickname;

    matchDetailDTO matchDetail;
    shootDTO shoot;
    List<shootDetailDTO> shootDetail = new ArrayList<shootDetailDTO>();
    passDTO pass;
    defenceDTO defence;
    List<playerDTO> player = new ArrayList<playerDTO>();

}
