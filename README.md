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

Benchmark revision: https://github.com/komamitsu/benchmark-serializers/tree/89762da3d25709919f803b6268343d79bf3b52c8

```
Benchmark                                                               Mode  Cnt     Score     Error   Units
BenchWithMediaItem.readMediaItemFromCbor                               thrpt    4   672.065 ±  19.631  ops/ms
BenchWithMediaItem.readMediaItemFromFuryJavaWithCodeGen                thrpt    4  6393.362 ±  29.240  ops/ms
BenchWithMediaItem.readMediaItemFromFuryJavaWithCodeGenThreadSafe      thrpt    4  6344.832 ± 618.473  ops/ms
BenchWithMediaItem.readMediaItemFromFuryJavaWithoutCodeGen             thrpt    4  3521.563 ±  47.437  ops/ms
BenchWithMediaItem.readMediaItemFromFuryJavaWithoutCodeGenThreadSafe   thrpt    4  3543.627 ±  18.601  ops/ms
BenchWithMediaItem.readMediaItemFromFuryXlangWithCodeGen               thrpt    4  3873.065 ± 395.235  ops/ms
BenchWithMediaItem.readMediaItemFromFuryXlangWithCodeGenThreadSafe     thrpt    4  4459.656 ±  66.322  ops/ms
BenchWithMediaItem.readMediaItemFromFuryXlangWithoutCodeGen            thrpt    4  2974.027 ±  54.305  ops/ms
BenchWithMediaItem.readMediaItemFromFuryXlangWithoutCodeGenThreadSafe  thrpt    4  2922.161 ±  36.546  ops/ms
BenchWithMediaItem.readMediaItemFromJsonWithFastjson                   thrpt    4  2288.073 ±  11.377  ops/ms
BenchWithMediaItem.readMediaItemFromJsonWithGson                       thrpt    4  1048.306 ±   5.207  ops/ms
BenchWithMediaItem.readMediaItemFromJsonWithJackson                    thrpt    4   560.613 ±  19.993  ops/ms
BenchWithMediaItem.writeMediaItemToCbor                                thrpt    4  1250.170 ±  15.814  ops/ms
BenchWithMediaItem.writeMediaItemToFuryJavaWithCodeGen                 thrpt    4  8176.200 ±  83.909  ops/ms
BenchWithMediaItem.writeMediaItemToFuryJavaWithCodeGenThreadSafe       thrpt    4  8310.323 ±  61.260  ops/ms
BenchWithMediaItem.writeMediaItemToFuryJavaWithoutCodeGen              thrpt    4  3883.194 ±  18.826  ops/ms
BenchWithMediaItem.writeMediaItemToFuryJavaWithoutCodeGenThreadSafe    thrpt    4  4094.552 ±  30.858  ops/ms
BenchWithMediaItem.writeMediaItemToFuryXlangWithCodeGen                thrpt    4  4181.741 ±  76.932  ops/ms
BenchWithMediaItem.writeMediaItemToFuryXlangWithCodeGenThreadSafe      thrpt    4  4520.429 ±  52.837  ops/ms
BenchWithMediaItem.writeMediaItemToFuryXlangWithoutCodeGen             thrpt    4  3740.068 ±  28.866  ops/ms
BenchWithMediaItem.writeMediaItemToFuryXlangWithoutCodeGenThreadSafe   thrpt    4  3508.202 ±  77.035  ops/ms
BenchWithMediaItem.writeMediaItemToJsonWithFastjson                    thrpt    4  2937.689 ±   7.646  ops/ms
BenchWithMediaItem.writeMediaItemToJsonWithGson                        thrpt    4   456.343 ±  10.369  ops/ms
BenchWithMediaItem.writeMediaItemToJsonWithJackson                     thrpt    4  1069.781 ±   7.994  ops/ms
```

![Screenshot from 2024-12-04 14-05-59](https://github.com/user-attachments/assets/55e39437-ea51-4c1a-b82e-5c8f5b8b228b)
![Screenshot from 2024-12-04 14-06-25](https://github.com/user-attachments/assets/ebfd3f35-062d-480a-9cdc-072f6ce42319)

