package com.yuuki.cooky.music.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yuuki.cooky.music.config.Constant;
import com.yuuki.cooky.music.enums.PlatformEnum;
import com.yuuki.cooky.music.service.IMusicApiService;
import com.yuuki.cooky.music.utils.CloudMusicEncryptUtil;
import com.yuuki.cooky.music.vo.SongVo;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("cloudMusicApiService")
@Slf4j
public class CloudMusicApiServiceImpl implements IMusicApiService {

    @Override
    public List<SongVo> search(String keyword) {
        Map<String,Object> forms = new HashMap<>();
        forms.put("s",keyword);
        forms.put("offset",0);
        forms.put("limit",20);
        forms.put("type",1);
        forms.put("csrf_token","");
        String result = Requests.post(Constant.CLOUD_MUSIC_SEARCH_API_URL).params(CloudMusicEncryptUtil.encrypt(JSON.toJSONString(forms))).send().readToText();
        List<JSONObject> jsonArray = JSONObject.parseObject(result).getJSONObject("result").getJSONArray("songs").toJavaList(JSONObject.class);
        return jsonArray.stream().map(json ->
            SongVo.builder()
                    .songName(json.getString("name"))
                    .songId(json.getString("id"))
                    .singer(json.getJSONArray("ar").toJavaList(JSONObject.class).stream().map(item -> item.getString("name")).collect(Collectors.joining("|")))
                    .img(json.getJSONObject("al").getString("picUrl"))
                    .time(json.getLong("dt"))
                    .platformEnum(PlatformEnum.CLOUD_MUSIC)
                    .build()
        ).collect(Collectors.toList());
    }

    @Override
    public SongVo getSongUrl(SongVo songVo) {
        String data = "{\"ids\":" + Arrays.asList(songVo.getSongId()) + ",\"br\":128000,\"csrf_token\":\"\"}";
        Map<String, String> forms = CloudMusicEncryptUtil.encrypt(data);
        String text = Requests.post(Constant.CLOUD_MUSIC_SONG_URL).params(forms).send().readToText();
        JSONArray array = JSONObject.parseObject(text).getJSONArray("data");
        if(!array.isEmpty()){
            songVo.setSongUrl(array.getJSONObject(0).getString("url"));
            return songVo;
        }
        return null;
    }
}
