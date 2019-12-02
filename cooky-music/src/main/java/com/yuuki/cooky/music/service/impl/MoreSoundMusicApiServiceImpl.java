package com.yuuki.cooky.music.service.impl;

import com.yuuki.cooky.music.service.IMusicApiService;
import com.yuuki.cooky.music.vo.SongVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("moreSoundMusicApiServiceImpl")
public class MoreSoundMusicApiServiceImpl implements IMusicApiService {

    @Override
    public List<SongVo> search(String keyword) {
        return null;
    }

    @Override
    public SongVo getSongUrl(SongVo songVo) {
        return null;
    }
}
