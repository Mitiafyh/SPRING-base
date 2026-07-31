#!/bin/bash
mkdir -p bin

cp -r SRC/main/webapp/WEB-INF/*  bin/
javac -cp "lib/*" -d bin $(find SRC -name "*.java")
java -cp "bin:lib/*" com.tuto.Main
