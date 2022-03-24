rm -rf /log/*client*.log
echo "$(pwd)" >> /log/client_out.log
java -classpath /target/scala-2.12/client-benchmark-assembly-0.1.0-SNAPSHOT.jar bench.ClientBenchmark >> /log/client_out.log

