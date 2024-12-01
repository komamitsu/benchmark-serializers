package org.komamitsu.bench.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.*;

@JsonPropertyOrder({"content", "images"})
@JsonFormat(shape=JsonFormat.Shape.ARRAY)
public class MediaItemAsArray
{
    private List<Photo> _photos;
    private Content _content;

    public MediaItemAsArray() { }
    public MediaItemAsArray(Content c)
    {
        _content = c;
    }

    public void addPhoto(Photo p) {
        if (_photos == null) {
            _photos = new ArrayList<Photo>();
        }
        _photos.add(p);
    }

    public List<Photo> getImages() { return _photos; }

    public void setImages(List<Photo> p) { _photos = p; }
    
    public Content getContent() { return _content; }

    public void setContent(Content c) { _content = c; }

    public enum Player { JAVA, FLASH}
    public enum Size { SMALL, LARGE}

    /*
    /**********************************************************
    /* Helper types
    /**********************************************************
     */

    @JsonPropertyOrder({"uri","title","width","height","size"})
    @JsonFormat(shape=JsonFormat.Shape.ARRAY)
    public static class Photo
    {
        private String _uri;
        private String _title;
        private int _width;
        private int _height;
        private Size _size;
    
        public Photo() {}
        public Photo(String uri, String title, int w, int h, Size s)
        {
          _uri = uri;
          _title = title;
          _width = w;
          _height = h;
          _size = s;
        }
    
      public String getUri() { return _uri; }

      public void setUri(String u) { _uri = u; }

      public String getTitle() { return _title; }

      public void setTitle(String t) { _title = t; }

      public int getWidth() { return _width; }
    
      public void setWidth(int w) { _width = w; }

      public int getHeight() { return _height; }

      public void setHeight(int h) { _height = h; }

      public Size getSize() { return _size; }

      public void setSize(Size s) { _size = s; }
    }

    @JsonPropertyOrder({"uri","title","width","height","format","duration","size","bitrate","persons","player","copyright"})
    @JsonFormat(shape=JsonFormat.Shape.ARRAY)
    public static class Content
    {
        private Player _player;
        private String _uri;
        private String _title;
        private int _width;
        private int _height;
        private String _format;
        private long _duration;
        private long _size;
        private int _bitrate;
        private List<String> _persons;
        private String _copyright;
    
        public Content() { }

        public void addPerson(String p) {
            if (_persons == null) {
                _persons = new ArrayList<String>();
            }
            _persons.add(p);
        }
        
        public Player getPlayer() { return _player; }

        public void setPlayer(Player p) { _player = p; }

        public String getUri() { return _uri; }

        public void setUri(String u) {  _uri = u; }

        public String getTitle() { return _title; }

        public void setTitle(String t) {  _title = t; }

        public int getWidth() { return _width; }

        public void setWidth(int w) {  _width = w; }

        public int getHeight() { return _height; }

        public void setHeight(int h) {  _height = h; }

        public String getFormat() { return _format; }
    
        public void setFormat(String f) {  _format = f;  }

        public long getDuration() { return _duration; }

        public void setDuration(long d) {  _duration = d; }

        public long getSize() { return _size; }

        public void setSize(long s) {  _size = s; }

        public int getBitrate() { return _bitrate; }

        public void setBitrate(int b) {  _bitrate = b; }

        public List<String> getPersons() { return _persons; }

        public void setPersons(List<String> p) {  _persons = p; }

        public String getCopyright() { return _copyright; }

        public void setCopyright(String c) {  _copyright = c; }
    }
}
