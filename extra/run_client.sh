rm -rf /log/*client*.log

echo "$(pwd)" >> /log/client_out.log

#rm -rf /zio-http/example/target
# echo "BUILDING ASSEMBLY IN CLIENT" >> /log/client_out.log
# cd /client/zio-http
# echo ${pwd} >> /log/server_out.log
# sbt "project example" clean assembly
# sbt "project example" 
# echo "DONE BUILDING ASSEMBLY IN CLIENT" >> /log/client_out.log

# while sleep 2; do echo thinking; done

# java --version >> /log/out.log
java -classpath /target/scala-2.12/client-benchmark-assembly-0.1.0-SNAPSHOT.jar bench.ClientBenchmark >> /log/client_out.log
#java  -classpath /zio-http/jar/*assembly*.jar example.SimpleServer >> /log/server_out.log 
