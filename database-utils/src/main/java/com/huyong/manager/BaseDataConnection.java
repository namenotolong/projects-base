package com.huyong.manager;

import com.huyong.dao.entity.DataConnection;
import com.huyong.enums.DbType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseDataConnection implements IDataConnection {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public String getJdbcUrl(DataConnection dataConnection) {
        if (StringUtils.isEmpty(dataConnection.getDatabase())) {
            return String.format(dataConnection.getDbType().getJdbcFormat(), dataConnection.getHost(), "default");
        }
        return String.format(dataConnection.getDbType().getJdbcFormat(), dataConnection.getHost(), dataConnection.getDatabase());
    }


}
