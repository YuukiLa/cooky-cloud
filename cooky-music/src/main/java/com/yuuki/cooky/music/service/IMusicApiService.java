package com.yuuki.cooky.music.service;

import com.yuuki.cooky.music.vo.SongVo;

import java.util.List;

public interface IMusicApiService {

    List<SongVo> search(String keyword);

    SongVo getSongUrl(SongVo songVo);
}
