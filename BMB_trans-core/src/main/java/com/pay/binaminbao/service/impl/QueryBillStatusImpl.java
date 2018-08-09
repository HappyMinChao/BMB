package com.pay.binaminbao.service.impl;

import com.pay.binaminbao.beans.UnionPayBean;
import com.pay.binaminbao.service.BaseService;
import com.pay.binaminbao.service.QueryBillPayService;

import java.io.IOException;
import java.util.Map;

public class QueryBillStatusImpl extends BaseServiceImpl implements QueryBillPayService{
    @Override
    public Object preRequest(UnionPayBean reqUnionpayBean, Map params) throws IOException {
        return null;
    }

    @Override
    public Object postRequest(UnionPayBean responseUnionPayBean) {
        return null;
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
