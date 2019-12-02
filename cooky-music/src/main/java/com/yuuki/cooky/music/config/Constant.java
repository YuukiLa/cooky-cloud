package com.yuuki.cooky.music.config;

public abstract class Constant {

    public static final String QQ_MUSIC_SEARCH_API_URL = "https://c.y.qq.com/soso/fcgi-bin/client_search_cp?aggr=1&cr=1&flag_qc=0&p=1&n=30&w=";

    public static final String QQ_MUSIC_SONG_URL_TOKEN_URL = "https://c.y.qq.com/base/fcgi-bin/fcg_music_express_mobile3.fcg?format=json205361747&platform=yqq&cid=205361747&filename=C400%s.m4a&guid=806393858&songmid=%s";

    public static final String QQ_MUSIC_SONG_URL = "http://ws.stream.qqmusic.qq.com/%s?fromtag=0&guid=806393858&vkey=%s";

    public static final String QQ_MUSIC_SONG_ALBUM_URL = "http://imgcache.qq.com/music/photo/album_300/%d/300_albumpic_%s_0.jpg";

    public static final String CLOUD_MUSIC_SEARCH_API_URL = "https://music.163.com/weapi/cloudsearch/get/web";

    public static final String CLOUD_MUSIC_SONG_URL = "http://music.163.com/weapi/song/enhance/player/url";

    public static String getQqMusicSongUrlTokenUrl(String mid) {
        return String.format(QQ_MUSIC_SONG_URL_TOKEN_URL,mid,mid);
    }

    public static String getQqMusicSongAlbumUrl(String album) {
        return String.format(QQ_MUSIC_SONG_ALBUM_URL,Integer.parseInt(album)%100,album);
    }
    public static String getQqMusicSongUrl(String fileName,String vkey) {
        return String.format(QQ_MUSIC_SONG_URL,fileName,vkey);
    }
}
