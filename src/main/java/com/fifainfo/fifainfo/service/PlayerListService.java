package com.fifainfo.fifainfo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fifainfo.fifainfo.entity.PlayerList;
import com.fifainfo.fifainfo.repository.PlayerListRepository;
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

@Service
public class PlayerListService {
    @Autowired
    private PlayerListRepository playerListRepository;
    @Autowired
    private PlayerInfoService playerInfoService;
    @Autowired
    private CustomUserDetailService customUserDetailService;

    public String setPlayer(String nickname) {
        System.out.println("set" + nickname);

        if(  playerListRepository.findByNickname(nickname).isPresent()){
            System.out.println("Already exsist");
            return "Already exsist";
        }

        ///플레이어 리스트 생성
        String url = "https://static.api.nexon.co.kr/fifaonline4/latest/spid.json";

        RestTemplate playerTemplate = new RestTemplate();

        HttpHeaders playerheader = new HttpHeaders();
        playerheader.add("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJYLUFwcC1SYXRlLUxpbWl0IjoiNTAwOjEwIiwiYWNjb3VudF9pZCI6IjEzMDkwMDYwNTMiLCJhdXRoX2lkIjoiMiIsImV4cCI6MTY3NjIwNTY4OSwiaWF0IjoxNjYwNjUzNjg5LCJuYmYiOjE2NjA2NTM2ODksInNlcnZpY2VfaWQiOiI0MzAwMTE0ODEiLCJ0b2tlbl90eXBlIjoiQWNjZXNzVG9rZW4ifQ.POHkH_0wFAvhrOqqX98f1TorhamBwXQ7Bf0-0Y6l_Ck");
        HttpEntity<?> playerentity = new HttpEntity<>(playerheader);

        UriComponents playeruri = UriComponentsBuilder.fromHttpUrl(url).build();

        ResponseEntity<String> playersresult = playerTemplate.exchange(playeruri.toString(), HttpMethod.GET, playerentity, String.class);
        ObjectMapper playermapper = new ObjectMapper();
        HashMap<Long, String> playerList = new HashMap<Long, String>();
        try {

            JSONParser playerParser = new JSONParser();
            JSONArray playerObject = (JSONArray) playerParser.parse(playersresult.getBody());
            for (int i = 0; i < playerObject.size(); i++) {
                JSONObject player = (JSONObject) playerObject.get(i); // 가져온 배열에서 i번째 부분만 가져옴
                playerList.put((Long) player.get("id"), (String) player.get("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //플레이어 리스트 생성

        String getuser="https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname="+nickname;
        RestTemplate userrestTemplate = new RestTemplate();

        HttpHeaders userheader = new HttpHeaders();
        userheader.add("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJYLUFwcC1SYXRlLUxpbWl0IjoiNTAwOjEwIiwiYWNjb3VudF9pZCI6IjEzMDkwMDYwNTMiLCJhdXRoX2lkIjoiMiIsImV4cCI6MTY3NjIwNTY4OSwiaWF0IjoxNjYwNjUzNjg5LCJuYmYiOjE2NjA2NTM2ODksInNlcnZpY2VfaWQiOiI0MzAwMTE0ODEiLCJ0b2tlbl90eXBlIjoiQWNjZXNzVG9rZW4ifQ.POHkH_0wFAvhrOqqX98f1TorhamBwXQ7Bf0-0Y6l_Ck");
        HttpEntity<?> userentity = new HttpEntity<>(userheader);

        UriComponents useruri = UriComponentsBuilder.fromHttpUrl(getuser).build();

        ResponseEntity<String> userresultMap = userrestTemplate.exchange(useruri.toString(), HttpMethod.GET, userentity, String.class);
        String accessId="";
        try {
            JSONParser userjsonParser = new JSONParser();
            JSONObject userjsonObject = (JSONObject) userjsonParser.parse(userresultMap.getBody());
            System.out.println(userjsonObject.get("accessId"));
            accessId = (String)userjsonObject.get("accessId");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        int idx = 0;
        int offset = 100;
        boolean ck=true;
        while (ck) {
            System.out.println(Integer.toString(offset * idx));
            String playerurl = "https://api.nexon.co.kr/fifaonline4/v1.0/users/"+accessId+"/matches?matchtype=50&offset=" + Integer.toString(offset * idx) + "&limit=100";
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJYLUFwcC1SYXRlLUxpbWl0IjoiNTAwOjEwIiwiYWNjb3VudF9pZCI6IjEzMDkwMDYwNTMiLCJhdXRoX2lkIjoiMiIsImV4cCI6MTY3NjIwNTY4OSwiaWF0IjoxNjYwNjUzNjg5LCJuYmYiOjE2NjA2NTM2ODksInNlcnZpY2VfaWQiOiI0MzAwMTE0ODEiLCJ0b2tlbl90eXBlIjoiQWNjZXNzVG9rZW4ifQ.POHkH_0wFAvhrOqqX98f1TorhamBwXQ7Bf0-0Y6l_Ck");
            HttpEntity<?> entity = new HttpEntity<>(header);

            UriComponents uri = UriComponentsBuilder.fromHttpUrl(playerurl).build();

            ResponseEntity<String> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, String.class);

            try {

                JSONParser jsonParser = new JSONParser();
                JSONArray jsonObject = (JSONArray) jsonParser.parse(resultMap.getBody());
                System.out.println(idx);
                System.out.println(jsonObject.size());
                if (jsonObject.size() == 0) {
                    System.out.println("break!");
                    ck=false;
                    break;
                }
                for (int i = 0; i < jsonObject.size(); i++) {

                    String matchname = (String) jsonObject.get(i); // 가져온 배열에서 i번째 부분만 가져옴


                    url = "https://api.nexon.co.kr/fifaonline4/v1.0/matches/" + matchname;
                    restTemplate = new RestTemplate();

                    header = new HttpHeaders();
                    header.add("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJYLUFwcC1SYXRlLUxpbWl0IjoiNTAwOjEwIiwiYWNjb3VudF9pZCI6IjEzMDkwMDYwNTMiLCJhdXRoX2lkIjoiMiIsImV4cCI6MTY3NjIwNTY4OSwiaWF0IjoxNjYwNjUzNjg5LCJuYmYiOjE2NjA2NTM2ODksInNlcnZpY2VfaWQiOiI0MzAwMTE0ODEiLCJ0b2tlbl90eXBlIjoiQWNjZXNzVG9rZW4ifQ.POHkH_0wFAvhrOqqX98f1TorhamBwXQ7Bf0-0Y6l_Ck");
                    entity = new HttpEntity<>(header);

                    uri = UriComponentsBuilder.fromHttpUrl(url).build();

                    resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, String.class);

                    JSONObject jsonob = (JSONObject) jsonParser.parse(resultMap.getBody());
                    JSONArray matchInfos = (JSONArray) jsonob.get("matchInfo");

                    for (int j = 0; j < matchInfos.size(); j++) {
                        JSONObject match = (JSONObject) matchInfos.get(j);

                        if (((String) match.get("accessId")).equals(accessId)) {
                            JSONObject matchDetail = (JSONObject) match.get("matchDetail");
                            if (((Long) matchDetail.get("matchEndType")).equals(0l)) {
                                JSONArray players = (JSONArray) match.get("player");

                                for (int k = 0; k < players.size(); k++) {
                                    JSONObject player = (JSONObject) players.get(k);
                                    Long spId = (Long) player.get("spId");

                                    String playername = playerList.get(spId);
                                    JSONObject status = (JSONObject) player.get("status");
                                    if (!((Long) status.get("passTry")).equals(0l)) {
                                        playerInfoService.createPlayerInfo(playername,nickname);


                                        playerInfoService.updateInfo(nickname
                                                ,playername, 1, (Long) status.get("shoot"), (Long) status.get("effectiveShoot"), (Long) status.get("assist"), (Long) status.get("goal"));
                                    }



                                }
                            }


                        }
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            idx+=1;
        }
        return "fin";
    }

    public boolean findOther(String nickname) {
        if (playerListRepository.findByNickname(nickname).isPresent()) {
            return true;
        }
        return false;
    }

    public PlayerList findPlayer(String nickname) {
        return playerListRepository.findByNickname(nickname).get();
    }
}
