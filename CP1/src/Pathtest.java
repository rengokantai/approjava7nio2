import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by Hernan Y.Ke on 2015/11/2.
 */
public class Pathtest {
    public static void main(String[] args){
        Path path = Paths.get("C:",System.getProperty("user.name"), "download");
        System.out.println(path);
        Path filepath1 = Paths.get("CP1", "test.txt");
        Path filepath2 = Paths.get("CP1", "test2.txt");
        System.out.println(path.getRoot()); //Does not exist if no C:\
        System.out.println(path.subpath(0,2));
        System.out.println(path.getParent());
        URI uri = path.toUri();
        System.out.println(uri);
        System.out.println(filepath1.getRoot());//Does not exist.
        System.out.println(filepath1.getFileName());

        //path -> file
        File f = path.toFile();
        try {
            System.out.println(filepath1.toRealPath(LinkOption.NOFOLLOW_LINKS));//absolute links
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Stream<String> lines = Files.lines(filepath1);
            lines.forEach(System.out::println);
        }catch(IOException e){
            e.printStackTrace();
        }


        //Combine path
        System.out.println(path.resolve("test.txt"));
        //resolvesbiling
        System.out.println(path.resolveSibling("downloadnew"));
        //relativize. How to navigate from path1 to path2
        System.out.println(filepath1.relativize(path.getFileName()));

        try {
            //compare two paths, do not use equals method
            System.out.println(filepath1);
            System.out.println(filepath2.getParent().resolve("test2.txt"));
            System.out.println(Files.isSameFile(filepath1,filepath2.getParent().resolve("test.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //iterate path
        System.out.println("Now printing separated path");
        for(Path p : path){
            System.out.println(p);
        }
    }
}
