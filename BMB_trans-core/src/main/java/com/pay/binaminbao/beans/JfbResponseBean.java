package com.pay.binaminbao.beans;

/*{
　　"code":200,
　　"message":"message",
　　"datetime":1442906439, 
　　"result":{
　　"retcode":0,
　　"retmsg":"已处理"
　　},
　　"data":{
　　	
　　}
	}*/

public class JfbResponseBean {
    
        // http响应状态码
        private String code="200";
        // http响应消息
        private String message="服务器请求成功！";
        // 处理时间毫秒值
        private long datetime = System.currentTimeMillis();
        // 处理结果
        private DealResult result;
        // 返回的数据
        private Object data="";
    
        public JfbResponseBean() {

        }

        public JfbResponseBean(DealResult result) {
            this.result = result;
        }

        public JfbResponseBean(DealResult result, Object data) {
            this.result = result;
            this.data = data;
        }

        public JfbResponseBean(String code, DealResult result) {
            this.code = code;
            this.result = result;
        }
        
        public JfbResponseBean(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public long getDatetime() {
            return datetime;
        }

        public void setDatetime(long datetime) {
            this.datetime = datetime;
        }

        public DealResult getResult() {
            return result;
        }

        public void setResult(DealResult result) {
            this.result = result;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "JfbResponseBean [code=" + code + ", message=" + message
                    + ", datetime=" + datetime + ", result=" + result + ", data="
                    + data + "]";
        }

    }
