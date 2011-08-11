/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java7sample;

import com.sun.nio.zipfs.JarFileSystemProvider;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileSystemProvider;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java7sample.Java7Sample.MyResource;

/**
 *
 * @author TanakaTa
 */
public class FileSystemSample {
    public static class PrintFiles extends SimpleFileVisitor<Path> {

    //Print information about each type of file.
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
        if (attr.isSymbolicLink()) {
            System.out.format("Symbolic link: %s ", file);
        } else if (attr.isRegularFile()) {
            System.out.format("Regular file: %s ", file);
        } else {
            System.out.format("Other: %s ", file);
        }
        System.out.println("(" + attr.size() + "bytes)");
        return FileVisitResult.CONTINUE;
    }

    //Print each directory visited.
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        System.out.format("Directory: %s%n", dir);
        return FileVisitResult.CONTINUE;
    }

    //If there is some error accessing the file, let the user know.
    //If you don't override this method and an error occurs, an IOException 
    //is thrown.
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.err.println(exc);
        return FileVisitResult.CONTINUE;
    }
}
    
    public static void main(String[] args) throws IOException {
        
        FileSystem fs = FileSystems.getDefault();

        List<FileSystemProvider> providers 
        = FileSystemProvider.installedProviders();
 
        // FileSystemProviderの一覧を出力
        for (FileSystemProvider provider: providers) {
            System.out.println(provider.getScheme() + ": " 
                               + provider.getClass());
        }
        
        FileSystem fileSystem = FileSystems.getDefault();

        // foo.txtを表すPathオブジェクトを生成
        Path path1 = fileSystem.getPath("C:\\tmp\\hoge.txt");
        System.out.println(path1);

        // 可変長引数を使用して、生成
        Path path2 = fileSystem.getPath("C:", "tmp", "hoge.txt");
        System.out.println(path2);

        // Pathsクラスを使用して、生成
        Path path3 = Paths.get("C:\\tmp\\hoge.txt");
        System.out.println(path3);
        
        Path zipPath = Paths.get("E:\\tmp\\nge.zip"); 
        try (FileSystem jarFileSystem 
             = FileSystems.newFileSystem(zipPath,
                                         ClassLoader.getSystemClassLoader())) {
            for (Path root : jarFileSystem.getRootDirectories()) {
                PrintFiles pf = new PrintFiles();
                Files.walkFileTree(root, pf);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
//        Calendar gc = GregorianCalendar.getInstance();
//        gc.set(Calendar.YEAR, 2009);
//        System.out.println(gc.getWeekYear() + ":" + gc.getWeeksInWeekYear());
//        System.out.println();
        
//        String s = null;
//        switch (s) {
//            case "H":
//                break;
//            default:
//                System.out.println("def");
//                break;
//        }
        
//        try (MyResource rs = new MyResource();
//                MyResource rs1 = new MyResource();) {
//            rs.hoge();
//        } catch(Exception exception) {
//            exception.printStackTrace();
//            for (Throwable throwable : exception.getSuppressed()) {
//                throwable.printStackTrace();
//            }
//        }
      //  dumpAcl("E:\\tmp.txt");
//        FileVisitor<Path> myFileVisitor = new SimpleFileVisitor<> () { 
//
//            @Override
//            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attribs) { 
//               System.out.println("I'm about to visit the "+dir+" directory"); 
//               return FileVisitResult.CONTINUE;
//            }
//
//            
//            @Override 
//            public FileVisitResult visitFile(Path file, BasicFileAttributes attribs) {
//
//               System.out.println("I'm visiting file "+file+" which has size " +attribs.size());
//               return FileVisitResult.CONTINUE;
//            }
//
//        };
//        Path headDir = Paths.get("C:\\");
//        Files.walkFileTree(headDir, myFileVisitor);
    }
    
    public static void dumpAcl(String filePath) throws IOException {
        File file = new File(filePath);
        Path path = file.toPath();
        AclFileAttributeView aclView =
            Files.getFileAttributeView(path, AclFileAttributeView.class);
        for (AclEntry aclEntry : aclView.getAcl()) {
            System.out.println(aclEntry);
        }
    }
}
