import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
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

    private String pathToStringBracket(Path path) {
        if (!path.toFile().isFile()) {
            String addLeft = "[";
            String addRight = "]";
            return addLeft+ path.toString() + addRight;
        } else {
            return path.toString();
        }
    }
    private String pathToStringColor(Path path) {
        if (!path.toFile().isFile()) {
            String addLeft = ConsoleColors.BLUE;
            String addRight = ConsoleColors.RESET;
            return addLeft+ path.toString() + addRight;
        } else {
            return path.toString();
        }
    }

    public List<String> ls(String flags) throws IOException {
        Stream<Path> files = Files.list(this.path);
        List result = files
                .filter((path) -> {
                    if(flags.contains("--filter=")){
                        String text = flags.substring(flags.indexOf("--filter="),flags.indexOf(" ",flags.indexOf("--filter=")));

                    }
                })
                .sorted((path1, path2) -> {
                    return Boolean.compare(!Files.isDirectory(path1),!Files.isDirectory(path2));
                })
                .map((path) -> {
                    if(flags.contains("--colors")){
                        return pathToStringColor(path);
                    }
                    else{
                        return pathToStringBracket(path);
                    }

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
