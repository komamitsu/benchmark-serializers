# benchmark-serializers

This is a simple benchmarker using JMH to measure the performance of some serializers. The model class used in this benchmark is copied from https://github.com/FasterXML/jackson-benchmarks/blob/2.18/src/main/java/com/fasterxml/jackson/perf/model/MediaItems.java.

## Benchmark result (2024-12-01)

```
$ uname -a
Linux pop-os 6.9.3-76060903-generic #202405300957~1726766035~22.04~4092a0e SMP PREEMPT_DYNAMIC Thu S x86_64 x86_64 x86_64 GNU/Linux

$ free -h
               total        used        free      shared  buff/cache   available
Mem:            30Gi        10Gi        16Gi       324Mi       3.7Gi        17Gi
Swap:           19Gi       2.8Gi        17Gi

$ lscpu
Architecture:             x86_64
  CPU op-mode(s):         32-bit, 64-bit
  Address sizes:          48 bits physical, 48 bits virtual
  Byte Order:             Little Endian
CPU(s):                   16
  On-line CPU(s) list:    0-15
Vendor ID:                AuthenticAMD
  Model name:             AMD Ryzen 9 5900HS with Radeon Graphics
    CPU family:           25
    Model:                80
    Thread(s) per core:   2
    Core(s) per socket:   8
    Socket(s):            1
    Stepping:             0
    CPU max MHz:          4680.0000
    CPU min MHz:          400.0000
    ...
```

Benchmark revision: https://github.com/komamitsu/benchmark-serializers/commit/ab980081f4e4baa9466a3575b368a5c062c44764

```
Benchmark                                                    Mode  Cnt     Score     Error   Units
BenchWithMediaItem.readMediaItemFromCbor                    thrpt    4   661.358 ±  39.717  ops/ms
BenchWithMediaItem.readMediaItemFromFuryJavaWithCodeGen     thrpt    4  6534.142 ± 368.589  ops/ms
BenchWithMediaItem.readMediaItemFromFuryJavaWithoutCodeGen  thrpt    4  3640.171 ±  37.897  ops/ms
BenchWithMediaItem.readMediaItemFromFuryXlang               thrpt    4  4335.869 ±  41.599  ops/ms
BenchWithMediaItem.readMediaItemFromJsonString              thrpt    4   536.589 ±  15.837  ops/ms
BenchWithMediaItem.writeMediaItemToCbor                     thrpt    4  1233.686 ± 113.363  ops/ms
BenchWithMediaItem.writeMediaItemToFuryJavaWithCodeGen      thrpt    4  8695.119 ±  36.664  ops/ms
BenchWithMediaItem.writeMediaItemToFuryJavaWithoutCodeGen   thrpt    4  4091.679 ±  11.203  ops/ms
BenchWithMediaItem.writeMediaItemToFuryXlang                thrpt    4  4441.156 ±  20.416  ops/ms
BenchWithMediaItem.writeMediaItemToJsonString               thrpt    4  1043.527 ±  23.261  ops/ms
```
