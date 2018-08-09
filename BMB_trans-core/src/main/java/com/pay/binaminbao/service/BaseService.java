package com.pay.binaminbao.service;

import com.pay.binaminbao.beans.UnionPayBean;

import java.util.Map;

public interface BaseService {
    public Object dealRequest(UnionPayBean reqUnionpayBean , Map params);    
}
