package com.luck.picture.lib.utils;

import android.util.Log;


public class AlbumNameHelper {

    /**
     * 智能翻译相册名：
     * - 如果名称中包含中文 直接返回原名；
     * - 如果是纯英文/符号，则尝试翻译为中文；
     * - 无法识别的英文，返回兜底值 "图库"。
     *
     * @param albumName 相册名称
     * @return 处理后的名称
     */
    public static String translateAlbumName(String albumName) {
        Log.d("folderName", "translateAlbumName: "+albumName);
        // 1. 判空
        if (albumName == null || albumName.trim().isEmpty()) {
            return "图库";
        }

        String trimmed = albumName.trim();

        // 2. 【关键】如果包含中文字符，直接返回原名（不翻译）
        if (containsChinese(trimmed)) {
            return trimmed;
        }

        // 3. 不含中文  转小写并尝试匹配英文
        String lowerName = trimmed.toLowerCase();

        switch (lowerName) {
            case "camera":
                return "相册";
            case "dcim":
                return "相机胶卷";
            case "screenshots":
                return "截图";
            case "download":
            case "downloads":
                return "下载";
            case "pictures":
                return "图片";
            case "recent":
            case "all photos":
            case "all images":
                return "所有照片";
            case "movies":
            case "videos":
                return "视频";
            case "audio":
            case "music":
                return "音频";
            default:
                return "图库";
        }
    }

    /**
     * 判断字符串是否包含中文字符
     */
    private static boolean containsChinese(String str) {
        if (str == null) return false;
        return str.matches(".*[\\u4e00-\\u9fa5].*");
    }
}