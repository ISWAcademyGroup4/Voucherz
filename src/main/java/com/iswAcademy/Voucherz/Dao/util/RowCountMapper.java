package com.iswAcademy.Voucherz.Dao.util;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class RowCountMapper implements RowMapper {
    @Override
    public Long mapRow(ResultSet rs, int rowNum) throws SQLException{
        return rs.getLong(1);
    }

}
