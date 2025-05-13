package com.egeo.components.product.common;

import com.egeo.orm.PageResult;
import com.egeo.utils.EmptyUtil;

public class PageResults<T> extends PageResult {

    @Override
    public boolean isFinished() {
        return EmptyUtil.isEmpty(this.getList());
    }
}
