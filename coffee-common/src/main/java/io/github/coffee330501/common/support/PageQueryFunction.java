package io.github.coffee330501.common.support;

import com.mybatisflex.core.paginate.Page;

public interface PageQueryFunction {
    Page<?> query(Number pageNumber, Number pageSize);
}
