package org.komamitsu.bench.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"uri", "title", "width", "height", "size"})
public class Image {
  private String _uri;
  private String _title;
  private int _width;
  private int _height;
  private Size _size;

  public Image() {}

  public Image(String uri, String title, int w, int h, Size s) {
    _uri = uri;
    _title = title;
    _width = w;
    _height = h;
    _size = s;
  }

  public String getUri() {
    return _uri;
  }

  public void setUri(String u) {
    _uri = u;
  }

  public String getTitle() {
    return _title;
  }

  public void setTitle(String t) {
    _title = t;
  }

  public int getWidth() {
    return _width;
  }

  public void setWidth(int w) {
    _width = w;
  }

  public int getHeight() {
    return _height;
  }

  public void setHeight(int h) {
    _height = h;
  }

  public Size getSize() {
    return _size;
  }

  public void setSize(Size s) {
    _size = s;
  }
}
