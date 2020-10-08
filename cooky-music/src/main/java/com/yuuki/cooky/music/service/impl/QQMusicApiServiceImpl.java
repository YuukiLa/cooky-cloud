package com.yuuki.cooky.music.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.yuuki.cooky.music.config.Constant;
import com.yuuki.cooky.music.enums.PlatformEnum;
import com.yuuki.cooky.music.service.IMusicApiService;
import com.yuuki.cooky.music.vo.SongVo;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("qqMusicApiService")
@RefreshScope
@Slf4j
public class QQMusicApiServiceImpl implements IMusicApiService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${qq.sign:1}")
    private String QQ_MUSIC_SIGN;
    @Value("${qq.cookie:{}}")
    private String QQ_MUSIC_COOKIE;
    Map<String, Object> cooikes;




    @PostConstruct
    public void init() {
        cooikes = new HashMap<>();
        String cookiesStr = "{\n" +
                "    \"eas_sid\":\"S1k5q8n691v7v0f634s3q9J7O8\",\n" +
                "    \"pgv_pvid\":\"4415647680\",\n" +
                "    \"pgv_pvi\":\"6936211456\",\n" +
                "    \"RK\":\"LKhc5/zTXL\",\n" +
                "    \"ptcz\":\"497897f9eeec9b93a46706391ce2ead9706d44b304fd00fd134243dbfb241368\",\n" +
                "    \"tvfe_boss_uuid\":\"25519e31d3f09ab1\",\n" +
                "    \"LW_uid\":\"K1w5x8V732x6e4w1p4y0b6W050\",\n" +
                "    \"pac_uid\":\"0_5ebcc485aa346\",\n" +
                "    \"LW_sid\":\"B1l5O9U0p5H7L9a6q5F4U2I9m0\",\n" +
                "    \"ts_uid\":\"3426505828\",\n" +
                "    \"psrf_qqopenid\":\"B53A10672D2EE87363A1C42EDD903D47\",\n" +
                "    \"psrf_qqrefresh_token\":\"BE53002C4426E657F20E005BDD98C5E5\",\n" +
                "    \"psrf_qqunionid\":\"9487EDAE62CB8012C3BCCBE7CBA34924\",\n" +
                "    \"psrf_qqaccess_token\":\"0FCF9C9DD6835C9C9C5ED0F2FACC74F8\",\n" +
                "    \"ts_refer\":\"ADTAGmyqq\",\n" +
                "    \"pgg_uid\":\"385866542\",\n" +
                "    \"pgg_appid\":\"101503919\",\n" +
                "    \"pgg_openid\":\"FE172DE7048C69F91AE191F24EAE847D\",\n" +
                "    \"pgg_access_token\":\"163147BDDF5F7F02F1C30E3A440C97D6\",\n" +
                "    \"pgg_type\":\"1\",\n" +
                "    \"pgg_user_type\":\"5\",\n" +
                "    \"uin_cookie\":\"o0806393858\",\n" +
                "    \"ied_qq\":\"o0806393858\",\n" +
                "    \"_qpsvr_localtk\":\"0.34519589717911003\",\n" +
                "    \"pgv_info\":\"ssid=s7073603580\",\n" +
                "    \"userAction\":\"1\",\n" +
                "    \"yqq_stat\":\"0\",\n" +
                "    \"uin\":\"806393858\",\n" +
                "    \"tmeLoginType\":\"2\",\n" +
                "    \"psrf_musickey_createtime\":\"1596357299\",\n" +
                "    \"euin\":\"NensoiEiNe4F\",\n" +
                "    \"psrf_access_token_expiresAt\":\"1604133299\",\n" +
                "    \"qqmusic_key\":\"Q_H_L_2ChUcy50eO3rJ6DUULRkQSYR59TWnI5RmeR86-eKJjINQ6R0ElCjMjDx29vFa88\",\n" +
                "    \"qm_keyst\":\"Q_H_L_2ChUcy50eO3rJ6DUULRkQSYR59TWnI5RmeR86-eKJjINQ6R0ElCjMjDx29vFa88\",\n" +
                "    \"player_exist\":\"1\",\n" +
                "    \"ts_last\":\"y.qq.com/portal/player.html\",\n" +
                "    \"qqmusic_fromtag\":\"66\",\n" +
                "    \"yplayer_open\":\"1\",\n" +
                "    \"yq_index\":\"0\"\n" +
                "}\n";
        cooikes = JSON.parseObject(cookiesStr, Map.class);


    }
        String paramStr = "{\"req\":{\"module\":\"CDN.SrfCdnDispatchServer\",\"method\":\"GetCdnDispatch\",\"param\":{\"guid\":\"4415647680\",\"calltype\":0,\"userip\":\"\"}},\"req_0\":{\"module\":\"vkey.GetVkeyServer\",\"method\":\"CgiGetVkey\",\"param\":{\"guid\":\"4415647680\",\"songmid\":[\"%s\"],\"songtype\":[0],\"uin\":\"806393858\",\"loginflag\":1,\"platform\":\"20\"}},\"comm\":{\"uin\":806393858,\"format\":\"json\",\"ct\":24,\"cv\":0}}";


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
                    .time(json.getLong("interval") * 1000)
                    .singer(singers.stream().map(singer -> singer.getString("name")).collect(Collectors.joining("|")))
                    .platformEnum(PlatformEnum.QQ_MUSIC)
                    .build();
        }).collect(Collectors.toList());
        return songs;
    }

    @Override
    public SongVo getSongUrl(SongVo songVo) {
        String data = String.format(paramStr, songVo.getSongId());
        Map<String, Object> cookie = cooikes = JSON.parseObject(QQ_MUSIC_COOKIE, Map.class);

        String result = Requests.get(Constant.getQqMusicSongUrlTokenUrl(QQ_MUSIC_SIGN,data)).cookies(cookie).send().readToText();
        JSONObject jsonObject = JSONObject.parseObject(result).getJSONObject("req_0").getJSONObject("data").getJSONArray("midurlinfo").getJSONObject(0);
        songVo.setSongUrl(Constant.getQqMusicSongUrl(jsonObject.getString("purl")));
        log.info("songVo:{}",songVo);
        songVo.setImg(Constant.getQqMusicSongAlbumUrl(songVo.getImg()));
        return songVo;
    }

    @Override
    public Object getPlayList() {
        try {
            Map<String, Object> resultMap = new HashMap<>();
            Map<String, String> header = new HashMap<>();
            header.put("referer", "http://y.qq.com/");
            header.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
//            List<PlayList> lists = new ArrayList<>();
            Map<String, String> param = Maps.newHashMap();
            param.put("sortId", "5");
            param.put("sin", 1 + "");
            param.put("ein", 1 + 29 + "");
            param.put("jsonpCallback", "getPlaylist");
            param.put("categoryId", 165 + "");
            String result = Requests.get("https://y.qq.com/n/yqq/playlist/").headers(header).params(param).send().readToText();
            //  System.out.println(result);
            result = result.replace("getPlaylist(", "").replace(")", "");
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONArray list = jsonObject.getJSONObject("data").getJSONArray("list");
            if (list.isEmpty()) {
//                throw new FailedCrawlResultException();
            }
            return list;
//            for (int i = 0; i < list.size(); i++) {
//                JSONObject object = list.getJSONObject(i);
//                PlayList playList = new PlayList();
//                playList.setId(object.getString("dissid"));
//                playList.setImgUrl(object.getString("imgurl"));
//                playList.setTitle(object.getString("dissname"));
//                playList.setUrl(BASE_URL + object.getString("dissid") + ".html");
//                playList.setCount(object.getString("listennum"));
//                playList.setPlatformCode(3);
//                playList.setType(type);
//                lists.add(playList);
//            }
//            resultMap.put("playList", lists);
//            resultMap.put("nextUrl", ++curr);
//            return resultMap;
        } catch (Exception e) {
//            throw new FailedCrawlResultException("未抓取到数据");
        }
        return null;
    }
}
