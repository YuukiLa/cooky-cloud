package com.yuuki.cooky.music.vo;

import com.yuuki.cooky.music.enums.PlatformEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongVo implements Serializable {

    private static final long serialVersionUID = -2435994361430403165L;

    /**
     * 歌曲id
     */
    private String songId;
    /**
     * 歌曲名字
     */
    private String songName;
    /**
     * 歌手名字
     */
    private String singer;
    /**
     * 图片
     */
    private String img;
    /**
     * 平台
     */
    private PlatformEnum platformEnum;
    /**
     * 歌曲时间
     */
    private Long time;
    /**
     * 歌曲地址
     */
        private String songUrl;

}
