package org.komamitsu.bench;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.databind.CBORMapper;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.gson.Gson;
import org.apache.fury.Fury;
import org.apache.fury.ThreadLocalFury;
import org.apache.fury.ThreadSafeFury;
import org.apache.fury.config.Language;
import org.komamitsu.bench.model.*;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(1)
@Measurement(time = 3, iterations = 4)
@Warmup(iterations = 2)
public class BenchWithMediaItem {
  private static final MediaItem MEDIA_ITEM = MediaItems.stdMediaItem();
  @LazyInit
  private ObjectMapper objectMapper;
  @LazyInit
  private CBORMapper cborMapper;
  @LazyInit
  private Gson gson;
  @LazyInit
  private Fury furyJavaWithCodeGen;
  @LazyInit
  private Fury furyJavaWithoutCodeGen;
  @LazyInit
  private Fury furyXlangWithCodeGen;
  @LazyInit
  private Fury furyXlangWithoutCodeGen;
  @LazyInit
  private ThreadSafeFury furyJavaWithCodeGenThreadSafe;
  @LazyInit
  private ThreadSafeFury furyJavaWithoutCodeGenThreadSafe;
  @LazyInit
  private ThreadSafeFury furyXlangWithCodeGenThreadSafe;
  @LazyInit
  private ThreadSafeFury furyXlangWithoutCodeGenThreadSafe;
  @LazyInit
  private String jsonData;
  @LazyInit
  private byte[] cborData;
  @LazyInit
  private byte[] furyJavaData;
  @LazyInit
  private byte[] furyXlangWithCodeGenData;
  @LazyInit
  private byte[] furyXlangWithoutCodeGenData;

  @Setup
  public void setup() throws JsonProcessingException {
    objectMapper = new ObjectMapper();
    jsonData = objectMapper.writeValueAsString(MEDIA_ITEM);
    cborMapper = new CBORMapper();
    cborData = cborMapper.writeValueAsBytes(MEDIA_ITEM);
    gson = new Gson();
    furyJavaWithCodeGen = Fury.builder().withLanguage(Language.JAVA).withCodegen(true).build();
    furyJavaWithoutCodeGen = Fury.builder().withLanguage(Language.JAVA).withCodegen(false).build();
    furyXlangWithCodeGen = Fury.builder().withLanguage(Language.XLANG).withCodegen(true).build();
    furyXlangWithoutCodeGen = Fury.builder().withLanguage(Language.XLANG).withCodegen(false).build();
    for (Fury fury : Arrays.asList(furyJavaWithCodeGen, furyJavaWithoutCodeGen, furyXlangWithCodeGen, furyXlangWithoutCodeGen)) {
      registerTargetClassesToFury(fury);
    }
    furyJavaWithCodeGenThreadSafe = new ThreadLocalFury(classLoader -> {
      Fury fury = Fury.builder().withLanguage(Language.JAVA).withCodegen(true).withClassLoader(classLoader).build();
      registerTargetClassesToFury(fury);
      return fury;
    });
    furyJavaWithoutCodeGenThreadSafe = new ThreadLocalFury(classLoader -> {
      Fury fury = Fury.builder().withLanguage(Language.JAVA).withCodegen(false).withClassLoader(classLoader).build();
      registerTargetClassesToFury(fury);
      return fury;
    });
    furyXlangWithCodeGenThreadSafe = new ThreadLocalFury(classLoader -> {
      Fury fury = Fury.builder().withLanguage(Language.XLANG).withCodegen(true).withClassLoader(classLoader).build();
      registerTargetClassesToFury(fury);
      return fury;
    });
    furyXlangWithoutCodeGenThreadSafe = new ThreadLocalFury(classLoader -> {
      Fury fury = Fury.builder().withLanguage(Language.XLANG).withCodegen(false).withClassLoader(classLoader).build();
      registerTargetClassesToFury(fury);
      return fury;
    });
    furyJavaData = furyJavaWithoutCodeGen.serialize(MEDIA_ITEM);
    furyXlangWithCodeGenData = furyXlangWithCodeGen.serialize(MEDIA_ITEM);
    furyXlangWithoutCodeGenData = furyXlangWithoutCodeGen.serialize(MEDIA_ITEM);
  }

  private void registerTargetClassesToFury(Fury fury) {
    fury.register(Image.class);
    fury.register(Size.class);
    fury.register(MediaContent.class);
    fury.register(MediaContent.Player.class);
    fury.register(MediaItem.class);
  }

  @TearDown
  public void tearDown() {
  }

  @Benchmark
  public void writeMediaItemToJsonWithJackson() throws JsonProcessingException {
    objectMapper.writeValueAsString(MEDIA_ITEM);
  }

  @Benchmark
  public void writeMediaItemToCbor() throws JsonProcessingException {
    cborMapper.writeValueAsBytes(MEDIA_ITEM);
  }

  @Benchmark
  public void writeMediaItemToJsonWithGson() throws JsonProcessingException {
    gson.toJson(MEDIA_ITEM);
  }

  @Benchmark
  public void writeMediaItemToJsonWithFastjson() throws JsonProcessingException {
    JSON.toJSONString(MEDIA_ITEM);
  }

  @Benchmark
  public void writeMediaItemToFuryJavaWithCodeGen() {
    furyJavaWithCodeGen.serialize(MEDIA_ITEM);
  }

  @Benchmark
  public void writeMediaItemToFuryJavaWithoutCodeGen() {
    furyJavaWithoutCodeGen.serialize(MEDIA_ITEM);
  }

  @Benchmark
  public void writeMediaItemToFuryXlangWithCodeGen() {
    furyXlangWithCodeGen.serialize(MEDIA_ITEM);
  }

  @Benchmark
  public void writeMediaItemToFuryXlangWithoutCodeGen() {
    furyXlangWithoutCodeGen.serialize(MEDIA_ITEM);
  }

  @Benchmark
  public void writeMediaItemToFuryJavaWithCodeGenThreadSafe() {
    furyJavaWithCodeGenThreadSafe.serialize(MEDIA_ITEM);
  }

  @Benchmark
  public void writeMediaItemToFuryJavaWithoutCodeGenThreadSafe() {
    furyJavaWithoutCodeGenThreadSafe.serialize(MEDIA_ITEM);
  }

  @Benchmark
  public void writeMediaItemToFuryXlangWithCodeGenThreadSafe() {
    furyXlangWithCodeGenThreadSafe.serialize(MEDIA_ITEM);
  }

  @Benchmark
  public void writeMediaItemToFuryXlangWithoutCodeGenThreadSafe() {
    furyXlangWithoutCodeGenThreadSafe.serialize(MEDIA_ITEM);
  }

  @Benchmark
  public void readMediaItemFromJsonWithJackson() throws JsonProcessingException {
    objectMapper.readValue(jsonData, MediaItem.class);
  }

  @Benchmark
  public void readMediaItemFromCbor() throws IOException {
    cborMapper.readValue(cborData, MediaItem.class);
  }

  @Benchmark
  public void readMediaItemFromJsonWithGson() throws JsonProcessingException {
    gson.fromJson(jsonData, MediaItem.class);
  }

  @Benchmark
  public void readMediaItemFromJsonWithFastjson() throws JsonProcessingException {
    JSON.parseObject(jsonData, MediaItem.class);
  }

  @Benchmark
  public void readMediaItemFromFuryJavaWithCodeGen() {
    furyJavaWithCodeGen.deserialize(furyJavaData);
  }

  @Benchmark
  public void readMediaItemFromFuryJavaWithoutCodeGen() {
    furyJavaWithoutCodeGen.deserialize(furyJavaData);
  }

  @Benchmark
  public void readMediaItemFromFuryXlangWithCodeGen() {
    furyXlangWithCodeGen.deserialize(furyXlangWithCodeGenData);
  }

  @Benchmark
  public void readMediaItemFromFuryXlangWithoutCodeGen() {
    furyXlangWithoutCodeGen.deserialize(furyXlangWithoutCodeGenData);
  }

  @Benchmark
  public void readMediaItemFromFuryJavaWithCodeGenThreadSafe() {
    furyJavaWithCodeGenThreadSafe.deserialize(furyJavaData);
  }

  @Benchmark
  public void readMediaItemFromFuryJavaWithoutCodeGenThreadSafe() {
    furyJavaWithoutCodeGenThreadSafe.deserialize(furyJavaData);
  }

  @Benchmark
  public void readMediaItemFromFuryXlangWithCodeGenThreadSafe() {
    furyXlangWithCodeGenThreadSafe.deserialize(furyXlangWithCodeGenData);
  }

  @Benchmark
  public void readMediaItemFromFuryXlangWithoutCodeGenThreadSafe() {
    furyXlangWithoutCodeGenThreadSafe.deserialize(furyXlangWithoutCodeGenData);
  }
}
