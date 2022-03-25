rm -rf /log/*client*.log

clientArray=("STTP" "ZHTTP" "HTTP4S")

for T in ${clientArray[@]} ; do CLIENT_TYPE=$T java -classpath /target/scala-2.12/client-benchmark-assembly-0.1.0-SNAPSHOT.jar bench.ClientBenchmark >> /log/results.log ; done
