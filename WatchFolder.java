package com.example.analyst;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class responsible to watch the input folder to parse new data files.
 */
@PropertySource("classpath:application.properties")
public class WatchFolder {

    private WatchKey wk;
    private WatchService watchService;
    private Parser parser;
    List<Path> files;

    @Value("system.data.in")
    private static String DATA_IN_FOLDER = "C:\\data\\in";

    @Value("system.extensions")
    private static String EXTENSION = ".dat";

    @Async
    public void watchInputFolder() {
        Path in = Paths.get(DATA_IN_FOLDER);
        try {

            File file = new File(DATA_IN_FOLDER);
            List<String> list = Arrays.asList(file.list());
            files = new ArrayList<>();

            try (DirectoryStream<Path> repositoryDirectory = Files.newDirectoryStream(in, new DirectoryStream.Filter<Path>() {
                @Override
                public boolean accept(Path entry) throws IOException {

                    String entryStr = entry.toString();

                    if (entryStr.endsWith(EXTENSION)) {
                        return true;
                    }
                    return false;
                }
            })) {

                for (Path patch : repositoryDirectory) {
                    files.add(patch);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            watchService = in.getFileSystem().newWatchService();
            in.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            while (true) {
                wk = watchService.take();
                for (WatchEvent<?> event : wk.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    if (StandardWatchEventKinds.OVERFLOW.equals(kind)) {
                        continue;
                    }
                    if (StandardWatchEventKinds.ENTRY_CREATE.equals(kind)) {
                        Path path = (Path) event.context();
                        if (path.toString().endsWith(EXTENSION)) {
                            wk.cancel();
                            parser = new Parser();
                            Path pathFile = Paths.get(DATA_IN_FOLDER + "\\" + path.toString());
                            parser.doParse(pathFile);
                        }
                    }
                }

                if (!wk.reset()) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (wk != null) {
                if (!wk.reset()) {
                    wk.cancel();
                }
            }
            try {
                watchService.close();
            } catch (IOException e) {
            }
        }
    }
}
