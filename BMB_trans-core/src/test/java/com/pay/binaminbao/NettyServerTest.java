package com.pay.binaminbao;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NettyServerTest {
    private static int MAXLENGTH = 2048;
    private static String  OPERATOR_CODE = "4209";
    private static String  LOGIN_KEY = "13e31fec4c";
    private static String  MAC_KEY = "c3a1c835bd";

    public static void main(String args[]){

        InputStream inputStream = null;
        OutputStream  outputStream = null;
        Socket socket = null;

        try {
            socket = new Socket("127.0.0.1", 9090);
            outputStream = socket.getOutputStream();
            String requestData = "hello world!";
            // 写出数据
            outputStream.write(requestData.getBytes());
            outputStream.flush();
            
            inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int readLen = inputStream.read(bytes);
            while (readLen != 0) {
                System.out.println(new String(bytes));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                if (outputStream != null)
                    outputStream.close();
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

