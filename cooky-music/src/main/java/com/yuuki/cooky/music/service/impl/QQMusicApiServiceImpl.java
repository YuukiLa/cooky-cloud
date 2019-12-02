package com.yuuki.cooky.music.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuuki.cooky.music.config.Constant;
import com.yuuki.cooky.music.enums.PlatformEnum;
import com.yuuki.cooky.music.service.IMusicApiService;
import com.yuuki.cooky.music.vo.SongVo;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("qqMusicApiService")
@Slf4j
public class QQMusicApiServiceImpl implements IMusicApiService {

    @Autowired
    RestTemplate restTemplate;

    Map<String,Object> cooikes;

    @PostConstruct
    public void  init() {
        cooikes = new HashMap<>();
        String cookiesStr = "{\n" +
                "\"pgv_pvi\":\"5226851328\",\n" +
                "\"RK\":\"uDgUs/zRHJ\",\n" +
                "\"ptcz\":\"530aeba18d952948bae0e2b53c37c2c75bc37e3464cbda820bc59e6fb9f9966d\",\n" +
                "\"pgv_pvid\":\"5801970496\",\n" +
                "\"o_cookie\":\"806393858\",\n" +
                "\"pac_uid\":\"1_806393858\",\n" +
                "\"tvfe_boss_uuid\":\"570b7b2708ee5460\",\n" +
                "\"LW_uid\":\"G1V5k493x086x0E8g1z2s0F6x4\",\n" +
                "\"LW_sid\":\"j1K5X6v9h0d3B2q435A2N0s7F6\",\n" +
                "\"ts_refer\":\"www.google.com/\",\n" +
                "\"ts_uid\":\"3761794214\",\n" +
                "\"eas_sid\":\"R185m7G1u9W210v8q0Y824S057\",\n" +
                "\"uin_cookie\":\"o0806393858\",\n" +
                "\"ied_qq\":\"o0806393858\",\n" +
                "\"pgv_info\":\"ssid=s2518792744\",\n" +
                "\"pgv_si\":\"s2316213248\",\n" +
                "\"userAction\":\"1\",\n" +
                "\"_qpsvr_localtk\":\"0.9931133919108892\",\"ptisp\":\"cnc\",\"uin\":\"806393858\",\"qm_keyst\":\"Q_H_L_28mL9t50e0TtNSst8rS8KHHhpMh62VybwZG4V_0Cq2g7bC7PRbzt5-HS62g8Q45\",\"psrf_musickey_createtime\":\"1573476098\",\"psrf_qqopenid\":\"B53A10672D2EE87363A1C42EDD903D47\",\"psrf_qqaccess_token\":\"857F637967068FFD997276555DDCE580\",\"psrf_access_token_expiresAt\":\"1581252098\",\"psrf_qqrefresh_token\":\"BE53002C4426E657F20E005BDD98C5E5\",\"psrf_qqunionid\":\"9487EDAE62CB8012C3BCCBE7CBA34924\",\"qqmusic_fromtag\":\"66\",\"yq_index\":\"0\",\"yqq_stat\":\"0\",\"yq_playschange\":\"0\",\"yq_playdata\":\"\",\"player_exist\":\"1\",\"ts_last\":\"y.qq.com/portal/player.html\",\"yplayer_open\":\"0\"}";
        cooikes = JSON.parseObject(cookiesStr,Map.class);
    }


    @Override
    public List<SongVo> search(String keyword) {
        String result = Requests.get(Constant.QQ_MUSIC_SEARCH_API_URL + keyword).send().readToText();
        result = result.substring(result.indexOf("(") + 1, result.length() - 1);
        JSONObject jsonObject = JSONObject.parseObject(result);
        List<JSONObject> jsonArray = jsonObject.getJSONObject("data")
                .getJSONObject("song")
                .getJSONArray("list").toJavaList(JSONObject.class);
        List<SongVo> songs = jsonArray.stream().map(json -> {
            List<JSONObject> singers = json.getJSONArray("singer").toJavaList(JSONObject.class);
            return SongVo.builder()
                    .songId(json.getString("songmid"))
                    .songName(json.getString("songname"))
                    .img(json.getString("albumid"))
                    .time(json.getLong("interval")*1000)
                    .singer(singers.stream().map(singer -> singer.getString("name")).collect(Collectors.joining("|")))
                    .platformEnum(PlatformEnum.QQ_MUSIC)
                    .build();
        }).collect(Collectors.toList());
        log.info(result);
        return songs;
    }

    @Override
    public SongVo getSongUrl(SongVo songVo) {
        String result = Requests.get(Constant.getQqMusicSongUrlTokenUrl(songVo.getSongId())).cookies(cooikes).send().readToText();
        JSONObject jsonObject = JSONObject.parseObject(result).getJSONObject("data").getJSONArray("items").getJSONObject(0);
        songVo.setSongUrl(Constant.getQqMusicSongUrl(jsonObject.getString("filename"),jsonObject.getString("vkey")));
        songVo.setImg(Constant.getQqMusicSongAlbumUrl(songVo.getImg()));
        return songVo;
    }
}
