package com.iswAcademy.Voucherz.dao.impl;

import com.iswAcademy.Voucherz.dao.AbstractBaseDao;
import com.iswAcademy.Voucherz.dao.IBaseDao;
import com.iswAcademy.Voucherz.dao.ITokenDao;
import com.iswAcademy.Voucherz.dao.util.RowCountMapper;
import com.iswAcademy.Voucherz.domain.BaseEntity;
import com.iswAcademy.Voucherz.domain.PasswordResetToken;
import com.iswAcademy.Voucherz.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class TokenDaoImpl extends AbstractBaseDao<PasswordResetToken> implements ITokenDao {


    @Autowired
    @Override
    public void setDataSource(@Qualifier(value="dataSource") DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        create = new SimpleJdbcCall(dataSource).withProcedureName("uspTokenGen").withReturnValue();
        update = new SimpleJdbcCall(dataSource).withProcedureName("uspUserUpdate").withReturnValue();
        find = new SimpleJdbcCall(dataSource).withProcedureName("uspFindUser").returningResultSet(SINGLE_RESULT,new BeanPropertyRowMapper<>(User.class));
    }

//    @Override
//    public PasswordResetToken findByEmail(String Email) {
//        SqlParameterSource in = new MapSqlParameterSource().addValue("email", Email);
//        Map<String,Object> m = find.execute(in);
//        List<PasswordResetToken> list = (List<PasswordResetToken>) m.get(SINGLE_RESULT);
//        if(list==null || list.isEmpty()){
//            return null;
//        }
//        return list.get(0);
//    }

    @Override
    public PasswordResetToken find(String email) {
        SqlParameterSource in = new MapSqlParameterSource().addValue("email", email);
        Map<String,Object> m = find.execute(in);
        List<PasswordResetToken>list = (List<PasswordResetToken>) m.get(SINGLE_RESULT);
        if(list==null || list.isEmpty()){
            return null;
        }
        return list.get(0);

    }

    @Override
    public PasswordResetToken findById(long id) {
        SqlParameterSource in = new MapSqlParameterSource().addValue("id", id);
        Map<String,Object> m = findById.execute(in);
        List<PasswordResetToken>list = (List<PasswordResetToken>) m.get(SINGLE_RESULT);
        if(list==null || list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
}
