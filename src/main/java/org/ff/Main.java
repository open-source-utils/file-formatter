package org.ff;

import org.ff.utils.CommandLineParser;

import static org.ff.connectors.ProcessConnector.initOperation;

public class Main {

    public static void main(String[] args) {
        CommandLineParser clp = new CommandLineParser(args);
        initOperation(clp);
        System.out.println("Files submitted, Bingo!!!!");
    }


}