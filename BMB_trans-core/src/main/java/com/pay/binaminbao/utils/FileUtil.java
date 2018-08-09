package com.pay.binaminbao.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import static com.pay.binaminbao.utils.SDKConstants.POINT;

public class FileUtil {

    private static Logger log;
    
    @PostConstruct
    private void init(){
        log = LoggerFactory.getLogger(SDKUtil.class);
    }
    
    /**
     * 文件拷贝方法
     * @param srcFile
     *            源文件
     * @param destFile
     *            目标文件
     * @return
     * @throws IOException
     */
    public static boolean copyFile(String srcFile, String destFile) {
        boolean flag = false;
        FileInputStream fin = null;
        FileOutputStream fout = null;
        FileChannel fcin = null;
        FileChannel fcout = null;
        try {
            // 获取源文件和目标文件的输入输出流
            fin = new FileInputStream(srcFile);
            fout = new FileOutputStream(destFile);
            // 获取输入输出通道
            fcin = fin.getChannel();
            fcout = fout.getChannel();
            // 创建缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                // clear方法重设缓冲区，使它可以接受读入的数据
                buffer.clear();
                // 从输入通道中将数据读到缓冲区
                int r = fcin.read(buffer);
                // read方法返回读取的字节数，可能为零，如果该通道已到达流的末尾，则返回-1
                if (r == -1) {
                    flag = true;
                    break;
                }
                // flip方法让缓冲区可以将新读入的数据写入另一个通道
                buffer.flip();
                // 从输出通道中将数据写入缓冲区
                fcout.write(buffer);
            }
            fout.flush();
        } catch (IOException e) {
            log.info("CopyFile fail", e);
        } finally {
            try {
                if (null != fin)
                    fin.close();
                if (null != fout)
                    fout.close();
                if (null != fcin)
                    fcin.close();
                if (null != fcout)
                    fcout.close();
            } catch (IOException ex) {
                log.info("Releases any system resources fail", ex);
            }
        }
        return flag;
    }

    /**
     * 写文件方法
     * @param filePath
     *            文件路径
     * @param fileContent
     *            文件内容
     * @param encoding
     *            编码
     * @return
     */
    public static boolean writeFile(String filePath, String fileContent,
                                    String encoding) {
        FileOutputStream fout = null;
        FileChannel fcout = null;
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }

        try {
            fout = new FileOutputStream(filePath);
            // 获取输出通道
            fcout = fout.getChannel();
            // 创建缓冲区
            // ByteBuffer buffer = ByteBuffer.allocate(1024);
            ByteBuffer buffer = ByteBuffer.wrap(fileContent.getBytes(encoding));
            fcout.write(buffer);
            fout.flush();
        } catch (FileNotFoundException e) {
            log.info("WriteFile fail", e);
            return false;
        } catch (IOException ex) {
            log.info("WriteFile fail", ex);
            return false;
        } finally {
            try {
                if (null != fout)
                    fout.close();
                if (null != fcout)
                    fcout.close();
            } catch (IOException ex) {
                log.info("Releases any system resources fail", ex);
                return false;
            }
        }
        return true;
    }

    /**
     * 将传入的文件名(xxx)改名 <br>
     * 结果为： xxx_backup.cer
     * @param fileName
     * @return
     */
    public static String genBackupName(String fileName) {
        if (StringUtils.isBlank(fileName))
            return "";
        int i = fileName.lastIndexOf(POINT);
        String leftFileName = fileName.substring(0, i);
        String rightFileName = fileName.substring(i + 1);
        String newFileName = leftFileName + "_backup" + POINT + rightFileName;
        return newFileName;
    }


    public static byte[] readFileByNIO(String filePath) {
        FileInputStream in = null;
        FileChannel fc = null;
        ByteBuffer bf = null;
        try {
            in = new FileInputStream(filePath);
            fc = in.getChannel();
            bf = ByteBuffer.allocate((int) fc.size());
            fc.read(bf);
            return bf.array();
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        } finally {
            try {
                if (null != fc) {
                    fc.close();
                }
                if (null != in) {
                    in.close();
                }
            } catch (Exception e) {
                log.info(e.getMessage());
                return null;
            }
        }
    }

    /**
     * 解压缩.
     * @param inputByte
     *            byte[]数组类型的数据
     * @return 解压缩后的数据
     * @throws IOException
     */
    public static byte[] inflater(final byte[] inputByte) throws IOException {
        int compressedDataLength = 0;
        Inflater compresser = new Inflater(false);
        compresser.setInput(inputByte, 0, inputByte.length);
        ByteArrayOutputStream o = new ByteArrayOutputStream(inputByte.length);
        byte[] result = new byte[1024];
        try {
            while (!compresser.finished()) {
                compressedDataLength = compresser.inflate(result);
                if (compressedDataLength == 0) {
                    break;
                }
                o.write(result, 0, compressedDataLength);
            }
        } catch (Exception ex) {
            System.err.println("Data format error!\n");
            ex.printStackTrace();
        } finally {
            o.close();
        }
        compresser.end();
        return o.toByteArray();
    }

    /**
     * 压缩.
     * @param inputByte
     *            需要解压缩的byte[]数组
     * @return 压缩后的数据
     * @throws IOException
     */
    public static byte[] deflater(final byte[] inputByte) throws IOException {
        int compressedDataLength = 0;
        Deflater compresser = new Deflater();
        compresser.setInput(inputByte);
        compresser.finish();
        ByteArrayOutputStream o = new ByteArrayOutputStream(inputByte.length);
        byte[] result = new byte[1024];
        try {
            while (!compresser.finished()) {
                compressedDataLength = compresser.deflate(result);
                o.write(result, 0, compressedDataLength);
            }
        } finally {
            o.close();
        }
        compresser.end();
        return o.toByteArray();
    }


}
