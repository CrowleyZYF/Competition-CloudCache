#!/bin/sh

rm *.class *.jar

cp ../WebContent/WEB-INF/lib/groovy-all-*.jar .

classes='../../WebContent/WEB-INF/classes'

mkdir cache-http
cd cache-http

mkdir emem
cp -r $classes/emem/cacheclient emem/cacheclient
mkdir emem/common
cp -r $classes/emem/common/data emem/common
jar -cvf cache-http.jar .
mv cache-http.jar ..
cd ..

mkdir cache-rmi
cd cache-rmi

mkdir -p emem/common
cp -r $classes/emem/common/rmi emem/common
jar -cvf cache-rmi.jar .
mv cache-rmi.jar ..
cd ..

classes='../WebContent/WEB-INF/classes'
cp $classes/HTTPClientDemo.class .
cp $classes/RMIClientDemo.class .
cp $classes/HTTPClientTest.class .
cp $classes/RMIClientTest.class .

rm -rf cache-client cache-rmi