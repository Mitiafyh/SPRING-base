javac -cp "lib/*" -d bin $(find SRC/main/java -name "*.java")
java -cp "bin:lib/*" com.tuto.Main
