package com.pay.binaminbao;

import com.pay.binaminbao.utils.AcpService;
import com.pay.binaminbao.utils.Constant;

import java.io.IOException;

public class CertificateTest {


    public static void main(String[] args){
        try {
            String s = AcpService.base64Decode("eyJ1c3JfbnVtIjoiMDgwMjAxODAyMDUwMjAwNzIifQ==", Constant.ENCODING);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
