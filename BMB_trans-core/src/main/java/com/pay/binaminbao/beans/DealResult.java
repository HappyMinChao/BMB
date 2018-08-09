package com.pay.binaminbao.beans;

public enum DealResult {
    
    SUCCESS				("0000","操作处理成功！"),

    //隐式登陆异常码开始
    INTELLEXCEPTION_INVALIDSN       ("1001","无效的机具序列号"),
    INTELLEXCEPTION_MORESN          ("1002","有多个机具序列号"),
    INTELLEXCEPTION_NOTRANSSN       ("1003","没有绑定机具"),
    INTELLEXCEPTION_NOCUSTOMER      ("1004","机具序列号不存在对应的客户号"),
    INTELLEXCEPTION_MORECUSTOMER    ("1005","机具序列号对应多个客户号"),
    INTELLEXCEPTION_NOOPERATOR      ("1006","机具序列号不存在对应的操作员"),
    INTELLEXCEPTION_MOREOPERATOR    ("1006","机具序列号对应多个操作员"),
    INTELLEXCEPTION_NOSHOP          ("1008","机具序列号不存在对应的店面信息"),
    INTELLEXCEPTION_MORESHOP        ("1009","机具序列号对应多个店面信息"),
    //隐式登陆异常码结束

    //登陆相关
    EXCEPTION			            ("99","服务器抛出异常！"),
    UNDEFINED_ERROR		            ("-1", "未知错误"),
    LOGIN_ERROR			            ("2000", "用户名或密码错误"),
    TOKEN_EXPIRE		            ("2001","免登录令牌失效"),
    NOLOGIN				            ("2002","用户未登录"),
    USER_UNEXIST		            ("2003","用户不存在"),
    PHONE_REGISTED		            ("2004","手机号已在机具上注册"),
    REQUEST_TIMEOUT		            ("2005","请求超时"),
    ILLEGAL_REQUEST		            ("2007","非法请求"),
    PARAM_LOSS			            ("2009","请求参数有误或者不完整"),
    DEVICE_UNBOUND		            ("2011","机具未绑定"),
    INVALID_SN			            ("2013","无效的机具序列号"),
    USER_MAXCOUNT		            ("2015","单机注册用户数超过九十九"),
    LOSS_INFO			            ("2017","缺失设备信息，用户登录需提供DeviceID"),
    LOSS_LOGINNO_OR_PHONENO         ("2019","缺少员工号或者手机号"),
    REPRINTBILL_NO_RESULT           ("2020","无要查询的小票信息"),
    REPRINTBILL_MORE_RESULT         ("2021","1个订单号有多条小票信息"),

    //扫码相关
    BARCODE_ORDER_EXCEPTION         ("3022","扫码支付下单操作异常"),
    BARCODE_QUERY_EXCEPTION         ("3023","扫码支付查询订单结果操作异常"),
    POSNOSHOP			            ("3024","POS没有店面信息"),
    CHECK_CODE_ERROR	            ("3025","验证码错误"),
    BARCODE_ORDER_REFUND_EXCEPTION	("3026","扫码支付退款操作异常"),
    BARCODE_RETURNEXCEPTION         ("3027", "聚合支付返回结果为空......"),

    //订单相关
    ORDER_QUERY_FAIL	            ("4001","订单列表查询失败"),
    VERSION_QUERY_FAIL	            ("4002","版本号查询失败"),
    ORDER_QUERY_SUM	                ("4003","订单汇总查询失败"),
    ORDER_NOT_EXIT                  ("4004","订单不存在"),

    //系统相关
    SERVER_EXCEPTION                ("9999","服务器异常")
    ;
    
    // 业务处理返回码
    private String retcode="0000";
    // 业务处理返回消息
    private String retmsg="操作处理成功！";

    DealResult(String retcode, String retmsg) {
        this.retcode = retcode;
        this.retmsg = retmsg;
    }
    
    @Override
    public String toString() {
        return "DealResult [retcode=" + retcode + ", retmsg=" + retmsg + "]";
    }

}
