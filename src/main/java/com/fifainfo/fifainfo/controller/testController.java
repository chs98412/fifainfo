package com.fifainfo.fifainfo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fifainfo.fifainfo.dto.*;
import com.fifainfo.fifainfo.entity.PlayerInfo;
import com.fifainfo.fifainfo.entity.PlayerList;
import com.fifainfo.fifainfo.entity.User;
import com.fifainfo.fifainfo.service.CustomUserDetailService;
import com.fifainfo.fifainfo.service.PlayerInfoService;
import com.fifainfo.fifainfo.service.PlayerListService;
import com.fifainfo.fifainfo.service.PlayerService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("test1")
public class testController {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private PlayerInfoService playerInfoService;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private PlayerListService playerListService;

    @GetMapping("/testtest")
    public String testtest() {
        return "testtest";
    }


    @PostMapping("/playerDetail")
    public playerDetailDTO playerDetail(@RequestBody playerDetailRequestDTO pl) {
        PlayerList playerList = playerListService.findPlayer(pl.getNickname());
        PlayerInfo playerInfo = playerInfoService.findInfo(playerList, pl.getPlayer());
        playerDetailDTO result= playerDetailDTO.builder()
                .spId(playerInfo.getSpId())
                .assist(playerInfo.getAssist())
                .effectiveShoot(playerInfo.getEffectiveShoot())
                .game(playerInfo.getGame())
                .goal(playerInfo.getGoal())
                .shoot(playerInfo.getShoot())
                .name(playerInfo.getName())
                .build();
        return result;
    }

    @PostMapping("/join")
    public UserDTO join(@RequestBody UserDTO userDTO) {
        customUserDetailService.joinUser(userDTO);
        playerListService.setPlayer(userDTO.getUserNickname());
        return userDTO;
    }

    @PostMapping("/login")
    public UserDTO login(@RequestBody LoginDTO loginDTO) {
        User user=customUserDetailService.loginUser(loginDTO);

        return UserDTO.builder()
                .userEmail(user.getUserEmail())
                .userNickname(user.getNickname())
                .build();
    }
    @PostMapping("/findother")
    public String findOther(@RequestBody FindOtherDTO findOtherDTO) {
        if (!playerListService.findOther(findOtherDTO.getNickname())) {
            System.out.println("there is no "+findOtherDTO.getNickname());
            playerListService.setPlayer(findOtherDTO.getNickname());
        }
        return findOtherDTO.getNickname();
    }


    @GetMapping("/set1")
    public String set1() {
        String url = "https://static.api.nexon.co.kr/fifaonline4/latest/spid.json";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJYLUFwcC1SYXRlLUxpbWl0IjoiNTAwOjEwIiwiYWNjb3VudF9pZCI6IjEzMDkwMDYwNTMiLCJhdXRoX2lkIjoiMiIsImV4cCI6MTY3NjIwNTY4OSwiaWF0IjoxNjYwNjUzNjg5LCJuYmYiOjE2NjA2NTM2ODksInNlcnZpY2VfaWQiOiI0MzAwMTE0ODEiLCJ0b2tlbl90eXBlIjoiQWNjZXNzVG9rZW4ifQ.POHkH_0wFAvhrOqqX98f1TorhamBwXQ7Bf0-0Y6l_Ck");
        HttpEntity<?> entity = new HttpEntity<>(header);

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

        ResponseEntity<String> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, String.class);
        ObjectMapper mapper = new ObjectMapper();
        HashMap<Long, String> playerList = new HashMap<Long, String>();
        try {

            JSONParser jsonParser = new JSONParser();
            JSONArray jsonObject = (JSONArray) jsonParser.parse(resultMap.getBody());
            for (int i = 0; i < jsonObject.size(); i++) {
                JSONObject player = (JSONObject) jsonObject.get(i); // ????????? ???????????? i?????? ????????? ?????????
                System.out.println(player);
//                playerService.create((Long)player.get("id"),(String)player.get("name"));
                playerList.put((Long) player.get("id"), (String) player.get("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(playerList);
        Long l = 318237967l;
        System.out.println(playerList.get(l));
        return "ok";
    }

    @GetMapping("/all")
    public List<PlayerInfo> all(@RequestParam String name) {

        return playerInfoService.all(name);
    }

    @PostMapping("/setuser")
    public HashMap<String, Object> setuser(@RequestBody UserDTO userDTO) {




        HashMap<String, Object> result = new HashMap<String, Object>();
        String jsonInString = "";


        ///???????????? ????????? ??????
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
                JSONObject player = (JSONObject) playerObject.get(i); // ????????? ???????????? i?????? ????????? ?????????
                playerList.put((Long) player.get("id"), (String) player.get("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //???????????? ????????? ??????

        String getuser="https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname="+userDTO.getUserNickname();
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

                    String matchname = (String) jsonObject.get(i); // ????????? ???????????? i?????? ????????? ?????????


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
                                        playerInfoService.createPlayerInfo(playername,spId,userDTO.getUserNickname());


                                        playerInfoService.updateInfo(userDTO.getUserNickname(),playername, 1, (Long) status.get("shoot"), (Long) status.get("effectiveShoot"), (Long) status.get("assist"), (Long) status.get("goal"));
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
            return result;



//        JSONObject dto = (JSONObject) jsonObject.get("nickname");
//        JSONArray docuArray = (JSONArray) result.get("body");
//
//        JSONObject docuObject = (JSONObject) docuArray.get(0);
//
//        System.out.println(docuObject.get("matchInfo").toString());

        //???????????? ????????? ?????? ???????????? ?????? string????????? ????????????
//        ObjectMapper mapper = new ObjectMapper();
//        System.out.println(mapper);
//        jsonInString = mapper.writeValueAsString(resultMap.getBody());




    }
}
