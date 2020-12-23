package bug.demo.twitter.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

@Service
public class FileDownloadService {

    private static final String FILE_NAME = "toBePostedToTwitter.jpg";

    public String donwloadFile(String fileURL) throws MalformedURLException {
        URL url = new URL(fileURL);
        try (ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME); FileChannel fileChannel = fileOutputStream.getChannel()) {

            fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return FILE_NAME;
    }

    public boolean moveFileToClassPath(String fileName) {
        File file = new File(fileName);
        return file.delete();
    }
}
