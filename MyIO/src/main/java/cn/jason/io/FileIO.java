package cn.jason.io;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @ClassName: FileIO
 * @Author: Jason
 * @Date: 2019/11/19 22:35
 * @Description: TODO
 */
public class FileIO {
    public static void main(String[] args) throws Exception {
//        readFileWithIO();
        URL url = FileIO.class.getClassLoader().getResource("example/IOTestFile.txt");
        readFileWithNio(url.getFile());
        readFileWithNioBigData(url.getFile());
//        readDirectory("C://");
    }

    private static void readFileWithIo() {
        InputStream in = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            URL url = FileIO.class.getClassLoader().getResource("example/IOTestFile.txt");
//            in = new BufferedInputStream(new FileInputStream("example/IOTestFile.txt"));
            in = url.openStream();
            if (in == null) {
                return;
            }

            isr = new InputStreamReader(in, StandardCharsets.UTF_8);
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readFileWithNioBigData(String path) throws Exception {
        AbstractReaderListener readerListener = new AbstractReaderListener() {
            @Override
            public void output(List<String> stringList) throws Exception {
                for (String s : stringList) {
                    System.out.println("s = " + s);
                }
            }
        };
        readerListener.setReadColNum(100000);
        NIOFileReader nioFileReader = new NIOFileReader(readerListener, "utf-8");
        nioFileReader.readFileByLine(path);
    }

    private static void readFileWithNio(String path) throws IOException {
        int bufSize = 1024;
        byte[] bs = new byte[bufSize];
        ByteBuffer byteBuf = ByteBuffer.allocate(1024);
        FileChannel channel = new RandomAccessFile(path, "r").getChannel();
        while (channel.read(byteBuf) != -1) {
            int size = byteBuf.position();
            byteBuf.rewind();
            byteBuf.get(bs);
            // 把文件当字符串处理，直接打印做为一个例子。
            System.out.print(new String(bs, 0, size));
            byteBuf.clear();
        }

    }


    /**
     * 读取目录
     *
     * @param pathStr
     * @throws IOException
     */
    public static void readDirectory(String pathStr) throws IOException {
        if (pathStr == null || pathStr.isEmpty()) {
            pathStr = "C:\\";
        }
        Path path = Paths.get(pathStr);
        System.out.println("文件名：" + path.getFileName());//文件或文件夹名称
        System.out.println("路径中名称元素的数量：" + path.getNameCount());//文件路径级别
        System.out.println("父目录路径：" + path.getParent());//父路径
        System.out.println("ROOT：" + path.getRoot());//根路径
        //遍历目录，过滤
//        DirectoryStream<Path> paths = Files.newDirectoryStream(path, "*.zip");
        DirectoryStream<Path> paths = Files.newDirectoryStream(path);
        for (Path p : paths) {
            File f = p.toFile();
            long size;
            if (f.isDirectory()) {
                size = FileUtils.sizeOfDirectory(p.toFile());
            } else {
                size = f.length();
            }
            long ms = size / 1024 / 1024;
            if (ms > 100) {
                System.out.println("file name = " + p.getFileName() + "; size ;" + ms + " MB");
            }
        }
    }
}
