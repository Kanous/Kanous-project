package utils;

import android.graphics.Bitmap;

import java.io.Serializable;


public class FileMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name = "";//歌曲名,电影名
    private String time = "";//创建时间
    private String path = "";//绝对路径
    private Bitmap bitmap;//专辑图片,或者视频第一帧
    private String fileName = "";//文件名1.ext
    private String author = "";//作者
    private boolean isForDelete = false;
    private int isLove = -1;//是否收藏
    private String album = "";//专辑名
    private String type = "";//文件地址类型
    private FileFormat fileFormate;//文件类型
    private String album_path = "";//专辑图片,或者视频第一帧存储地址
    private int isSupport = 0;//平台是否支持
    private String duration = "";

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getIsSupport() {
        return isSupport;
    }

    public void setIsSupport(int isSupport) {
        this.isSupport = isSupport;
    }

    public String getAlbum_path() {
        return album_path;
    }

    public void setAlbum_path(String album_path) {
        this.album_path = album_path;
    }

    public FileFormat getFileFormate() {
        return fileFormate;
    }

    public void setFileFormate(FileFormat fileFormate) {
        this.fileFormate = fileFormate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getIsLove() {
        return isLove;
    }

    public void setIsLove(int isLove) {
        this.isLove = isLove;
    }

    public boolean isForDelete() {
        return isForDelete;
    }

    public void setForDelete(boolean isForDelete) {
        this.isForDelete = isForDelete;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof FileMessage) {
            FileMessage fileMessage = (FileMessage) obj;
            if (fileMessage.getPath().equals(this.path)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 17 * path.hashCode();
    }
}
