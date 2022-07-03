package org.ff.utils;

import java.util.*;

public class CommandLineParser {
    List<String> args = new ArrayList<>();
    Map<String, List<String>> map = new HashMap<>();
    Set<String> flags = new HashSet<>();

    public CommandLineParser(String arguments[]) {
        this.args = Arrays.asList(arguments);
        map();
    }

    public String[] getArgumentValue(String argumentName) {
        if (map.containsKey(argumentName))
            return map.get(argumentName).toArray(new String[0]);
        else
            return null;
    }

    public void map() {
        for (String arg : args) {
            if (arg.startsWith("-")) {
                if (args.indexOf(arg) == (args.size() - 1)) {
                    flags.add(arg.replace("-", ""));
                } else if (args.get(args.indexOf(arg) + 1).startsWith("-")) {
                    flags.add(arg.replace("-", ""));
                } else {
                    List<String> argumentValues = new ArrayList<>();
                    int i = 1;
                    while (args.indexOf(arg) + i != args.size() && !args.get(args.indexOf(arg) + i).startsWith("-")) {
                        argumentValues.add(args.get(args.indexOf(arg) + i));
                        i++;
                    }
                    map.put(arg.replace("-", ""), argumentValues);
                }
            }
        }
    }
}
