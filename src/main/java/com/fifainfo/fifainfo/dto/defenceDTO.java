package com.fifainfo.fifainfo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder

public class defenceDTO {


    int blockTry;
    int blockSuccess;
    int tackleTry;
    int tackleSuccess;

}
