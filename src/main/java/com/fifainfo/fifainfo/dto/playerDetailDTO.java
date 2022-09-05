package com.fifainfo.fifainfo.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class playerDetailDTO {

    private String name;
    private Long spId;

    private int game;

    private Long shoot;
    private Long effectiveShoot;
    private Long assist;
    private Long goal;
}
