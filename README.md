# File formatter

## To build App

    mvn clean compile package

## To run App

    java -cp target/file-formatter-jar-with-dependencies.jar org.ff.Main -path "<path to input files>" -names file1.extention file2.extention -delimiters <delimiter for file 1> <delimiter for file 2>   

## To see output
    <path to input files>/output/
        file1.extention_currentTimestamp.jsonl
        file1.extention_currentTimestamp.jsonl