import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Dictionary;

/**
 * Created by Hernan Y.Ke on 2015/11/2.
 */
public class FilesandDictionaries {
    public static void main(String[] args) {
        Path path = Paths.get("CP1","test.txt");
        System.out.println(Files.exists(path,new LinkOption[]{LinkOption.NOFOLLOW_LINKS}));

        Path notexistpath = FileSystems.getDefault().getPath("CP4", "test.txt");
        System.out.println(Files.notExists(notexistpath));

        //Properties
        System.out.println(Files.isExecutable(path));
        System.out.println(Files.isReadable(path));
        System.out.println(Files.isWritable(path));
        System.out.println(Files.isRegularFile(path,LinkOption.NOFOLLOW_LINKS));

        //check same file, hidden file
        Path samepath =  Paths.get("CP1","test.txt");
        try {
            System.out.println(Files.isSameFile(path,samepath));
            System.out.println(Files.isHidden(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //List filesystem root dictionaries
        Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
        for(Path name: dirs){
            System.out.println(name);
        }

        //Old Java 6 way to show files

        File [] roots = File.listRoots();
        for(File f:roots){
            System.out.println(f);
        }
        System.out.println("\nBefore adding a filter");
        //Create a new dic
        Path newpath = FileSystems.getDefault().getPath("C:/path.txt");
        try {
            Files.createFile(newpath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //List the content of folder
        Path foldercontent = Paths.get("C:/");
        try(DirectoryStream<Path> ds = Files.newDirectoryStream(foldercontent) ){
            for(Path f:ds){
                System.out.println(f.getFileName());//API. getFileName()
            }
        }catch (IOException e){
            System.err.println(e);
        }

        System.out.println("\nAfter adding a filter");
        //Define a filter
        DirectoryStream.Filter<Path> dir_filter = new DirectoryStream.Filter<Path>(){
            public boolean accept(Path path) throws IOException{
                return (Files.size(path)> 2000);
            }
        };

        try(DirectoryStream<Path> ds = Files.newDirectoryStream(foldercontent, dir_filter)){
            for(Path fi: ds){
                System.out.println(fi.getFileName());
            }
        }catch (IOException e){
            System.out.println(e);
        }


    }
}
