import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileCommander {
    private Path path;

    public FileCommander() {
        path = Paths.get(System.getProperty("user.home"));
    }

    public String pwd() {
        return path.toString();
    }

    public void cd(String path) {
        this.path = this.path.resolve(path).normalize();
    }

    public File getFile() {
        return new File(this.path.toString());
    }

    private String pathToString(Path path) {
        if (!path.toFile().isFile()) {
            return "[" + path.toString() + "]";
        } else {
            return path.toString();
        }
    }

    public List<String> ls() throws IOException {
        Stream<Path> files = Files.list(this.path);
        List result = files
                .sorted((path1, path2) -> {
                    return Boolean.compare(!Files.isDirectory(path1),!Files.isDirectory(path2));
                })
                .map((path) -> {
                    return pathToString(path);
                })
                .collect(Collectors.toList());

        return result;
    }

    public List<String> find(String text) throws IOException{
        return Files.walk(this.path)
                .filter((path) -> {
                    return path.toString().contains(text);
                })
                .map(Path::toString)
                .collect(Collectors.toList());

    }
}
