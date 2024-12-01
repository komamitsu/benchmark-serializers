package org.komamitsu.bench;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.databind.CBORMapper;
import org.apache.fury.Fury;
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
  private ObjectMapper objectMapper;
  private CBORMapper cborMapper;
  private Fury furyJavaWithCodeGen;
  private Fury furyJavaWithoutCodeGen;
  private Fury furyXlang;
  private String jsonData;
  private byte[] cborData;
  private byte[] furyJavaData;
  private byte[] furyXlangData;

  @Setup
  public void setup() throws JsonProcessingException {
    objectMapper = new ObjectMapper();
    jsonData = objectMapper.writeValueAsString(MEDIA_ITEM);
    cborMapper = new CBORMapper();
    cborData = cborMapper.writeValueAsBytes(MEDIA_ITEM);
    furyJavaWithCodeGen = Fury.builder().withLanguage(Language.JAVA).withCodegen(true).build();
    furyJavaWithoutCodeGen = Fury.builder().withLanguage(Language.JAVA).withCodegen(false).build();
    furyXlang = Fury.builder().withLanguage(Language.XLANG).build();
    for (Fury fury : Arrays.asList(furyJavaWithCodeGen, furyJavaWithoutCodeGen, furyXlang)) {
      fury.register(Image.class);
      fury.register(Size.class);
      fury.register(MediaContent.class);
      fury.register(MediaContent.Player.class);
      fury.register(MediaItem.class);
    }
    furyJavaData = furyJavaWithoutCodeGen.serialize(MEDIA_ITEM);
    furyXlangData = furyXlang.serialize(MEDIA_ITEM);
  }

  @TearDown
  public void tearDown() {
  }

  @Benchmark
  public void writeMediaItemToJsonString() throws JsonProcessingException {
    objectMapper.writeValueAsString(MEDIA_ITEM);
  }

  @Benchmark
  public void writeMediaItemToCbor() throws JsonProcessingException {
    cborMapper.writeValueAsBytes(MEDIA_ITEM);
  }

  @Benchmark
  public void writeMediaItemToFuryJavaWithCodeGen() throws JsonProcessingException {
    furyJavaWithCodeGen.serialize(MEDIA_ITEM);
  }

  @Benchmark
  public void writeMediaItemToFuryJavaWithoutCodeGen() throws JsonProcessingException {
    furyJavaWithoutCodeGen.serialize(MEDIA_ITEM);
  }

  @Benchmark
  public void writeMediaItemToFuryXlang() throws JsonProcessingException {
    furyXlang.serialize(MEDIA_ITEM);
  }

  @Benchmark
  public void readMediaItemFromJsonString() throws JsonProcessingException {
    objectMapper.readValue(jsonData, MediaItem.class);
  }

  @Benchmark
  public void readMediaItemFromCbor() throws IOException {
    cborMapper.readValue(cborData, MediaItem.class);
  }

  @Benchmark
  public void readMediaItemFromFuryJavaWithCodeGen() throws IOException {
    furyJavaWithCodeGen.deserialize(furyJavaData);
  }

  @Benchmark
  public void readMediaItemFromFuryJavaWithoutCodeGen() throws IOException {
    furyJavaWithoutCodeGen.deserialize(furyJavaData);
  }

  @Benchmark
  public void readMediaItemFromFuryXlang() throws IOException {
    furyXlang.deserialize(furyXlangData);
  }
}
