package cn.ntshare.laboratory.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
public class FtpUtil {

    private String host;
    private Integer port;
    private String username;
    private String password;
    private FTPClient ftpClient;

    private FtpUtil() {
        this.host = "127.0.0.1";
        this.port = 21;
        this.username = "root";
        this.password = "root";
    }

    /**
     * 上传图片到FTP服务器
     *
     * @param file
     * @return
     */
    public static boolean uploadImg(File file) {
        return new FtpUtil().uploadFile("/images", file);
    }

    /**
     * 删除FTP服务器上的单个图片
     *
     * @param imgName
     * @return
     */
    public static boolean deleteImg(String imgName) {
        return new FtpUtil().deleteFile("/images", imgName);
    }

    /**
     * 删除多个文件
     *
     * @param imgNames
     */
    public static void deleteImgs(List<String> imgNames) {
        new FtpUtil().deleteFiles("/images", imgNames);
    }

    /**
     * 上传文件到FTP服务器
     *
     * @param path
     * @param file
     * @return
     */
    private boolean uploadFile(String path, File file) {
        try {
            if (connect()) {
                FileInputStream fileInputStream;
                ftpClient.changeWorkingDirectory(path);     // 切换工作目录
                ftpClient.setBufferSize(2048);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);      //设置文件格式为二进制格式
                ftpClient.enterLocalPassiveMode();
                fileInputStream = new FileInputStream(file);
                ftpClient.storeFile(file.getName(), fileInputStream);
                ftpClient.disconnect();
                fileInputStream.close();
                log.info("文件 {} 上传成功", file.getName());
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除FTP服务器中的文件
     *
     * @param path
     * @param fileName
     * @return
     */
    private boolean deleteFile(String path, String fileName) {
        boolean uploadResult = false;
        try {
            if (connect()) {
                ftpClient.changeWorkingDirectory(path);
                if (ftpClient.deleteFile(fileName)) {
                    log.info("文件 {} 删除成功", fileName);
                    uploadResult = true;
                } else {
                    log.info("文件 {} 删除失败", fileName);
                }
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadResult;
    }

    /**
     * 删除多个文件
     * @param path
     * @param imgNames
     * @return
     */
    private Integer deleteFiles(String path, List<String> imgNames) {
        int count = 0;
        try {
            if (connect()) {
                ftpClient.changeWorkingDirectory(path);
                for (String s : imgNames) {
                    if (ftpClient.deleteFile(s)) {
                        log.info("文件 {} 删除成功", s);
                        count++;
                    } else {
                        log.info("文件 {} 删除失败", s);
                    }
                }
                log.info("共删除 {} 张图片", count);
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            log.error("连接FTP服务器失败");
        }
        return count;
    }


    /**
     * 连接到FTP服务器
     *
     * @return
     */
    private boolean connect() {
        boolean connectResult = false;
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(host, port);
            connectResult = ftpClient.login(username, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connectResult;
    }

}
