package org.ff.connectors;

import org.ff.services.CallableFileHandler;
import org.ff.utils.CommandLineParser;
import org.ff.utils.DirUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import static org.ff.utils.Constants.*;
import static org.ff.utils.DateHelper.generateFileSuffix;

public class ProcessConnector {

    public static void initOperation(CommandLineParser clp) {
        String path = clp.getArgumentValue(PATH)[0];
        if (path == null || path.isEmpty()) {
            path = DirUtils.getJarDirectory() + FILE_DIR;
        }
        String names[] = clp.getArgumentValue(FILE_NAMES);
        String delimiters[] = clp.getArgumentValue(DELIMITERS);
        if(names !=null && delimiters!=null){
            long resultFuture = createThreads(path, names, delimiters);
            System.out.println((names.length == resultFuture? "All ": resultFuture) + "file(s) processed");
        }

    }

    private static long createThreads(String path, String[] names, String[] delimiters) {
        ExecutorService executor = Executors.newFixedThreadPool(names.length>4 ? 4 : names.length);
        List<FutureTask> taskList = new ArrayList<FutureTask>();
        long resultFuture = 0;
        submitJobs(path, names, delimiters, executor, taskList);
        for (FutureTask futureTask : taskList) {
            try {
                resultFuture += (Long)futureTask.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return resultFuture;
    }

    private static void submitJobs(String path, String[] names, String[] delimiters, ExecutorService executor, List<FutureTask> taskList) {
        if (delimiters.length == names.length) {
            for (int i = 0; i < names.length; i++) {
                char delimiter = delimiters[i].charAt(0);
                String outputPath = path + OUTPUT + names[i] + UNDERSCORE + generateFileSuffix()+ OUTPUT_EXT;
                String filePath = path + names[i];
                FutureTask futureTask = new FutureTask(new CallableFileHandler(delimiter, outputPath, filePath));
                taskList.add(futureTask);
                executor.execute(futureTask);
            }
            executor.shutdown();
        } else {
            System.out.println("Please provide delimiters for all input files");
        }
    }

}
