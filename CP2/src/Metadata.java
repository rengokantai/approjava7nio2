import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.util.Set;

/**
 * Created by Hernan Y.Ke on 2015/11/2.
 */
public class Metadata {
    public static void main(String[] args) {
        FileSystem fs = FileSystems.getDefault();
        Set<String> views = fs.supportedFileAttributeViews();
        for(String view : views){
            System.out.println(view);
        }
        System.out.println("==getFileStores==");

        for(FileStore store : fs.getFileStores()){
            boolean supported = store.supportsFileAttributeView(BasicFileAttributeView.class);
            System.out.println(supported);
        }
        System.out.println("==file exp==");
        Path path = Paths.get("CP1","test.txt");

        try {
            FileStore store = Files.getFileStore(path);
            boolean supported = store.supportsFileAttributeView("basic");
            System.out.println(store.name()+ "  "+ supported);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
