import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Hernan Y.Ke on 2015/11/2.
 */
public class SymbolicLink {
    /*
    Create a symbolic link:
    Windows:
    mklink /D newlinkfolder targetfolder
    linux:
    ln -s targetfolder newlinkfolder
     */
    public static void main(String[] args) {
        Path link = FileSystems.getDefault().getPath("D:/intro/new");
        Path target = FileSystems.getDefault().getPath("C:/Users");

        try {
            Files.createSymbolicLink(link,target);
        } catch (IOException|UnsupportedOperationException e) {
            e.printStackTrace();
            if(e instanceof UnsupportedOperationException){
                System.out.println("UnsupportOp!");
            }
        }


    }
}
