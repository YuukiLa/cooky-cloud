package com.yuuki.cooky.music.controller.v1;


import com.yuuki.cooky.common.model.Response;
import com.yuuki.cooky.music.service.IMusicApiService;
import com.yuuki.cooky.music.vo.SongVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/v1")
@Slf4j
public class MusicApiController {

    @Resource(name = "qqMusicApiService")
    IMusicApiService qqMusicApiService;
    @Resource(name = "cloudMusicApiService")
    IMusicApiService cloudMusicApiService;

    @GetMapping("/search")
    public Response searchMusic(String keyword) {
        List<SongVo> clouds = cloudMusicApiService.search(keyword);
        List<SongVo> qqs = qqMusicApiService.search(keyword);
        qqs.addAll(clouds);
        return Response.success("success",qqs);
    }


    @PostMapping("/song")
    public Response getSongDetail(@RequestBody SongVo songVo){
        switch (songVo.getPlatformEnum()){
            case CLOUD_MUSIC:
                return Response.success("success",cloudMusicApiService.getSongUrl(songVo));
            case QQ_MUSIC:
                return Response.success("success",qqMusicApiService.getSongUrl(songVo));
            default:
                return Response.faild("未知平台");
        }

    }

    @GetMapping("/playlist")
    public Response playlist() {
        return Response.success("success",qqMusicApiService.getPlayList());
    }

}
