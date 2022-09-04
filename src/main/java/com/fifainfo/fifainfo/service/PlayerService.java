package com.fifainfo.fifainfo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fifainfo.fifainfo.entity.Player;
import com.fifainfo.fifainfo.repository.PlayerRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;


    public Player create(Long spId, String name) {
        Optional<Player> player = playerRepository.findBySpId(spId);

        if (player.isPresent()) {
            return player.get();
        } else {
            Player pl = Player.builder()
                    .spId(spId)
                    .name(name)
                    .build();
            return playerRepository.save(pl);
        }
    }


}
