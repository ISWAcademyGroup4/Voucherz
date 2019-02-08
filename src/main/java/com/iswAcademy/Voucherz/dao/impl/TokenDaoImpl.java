package com.iswAcademy.Voucherz.dao.impl;

import com.iswAcademy.Voucherz.dao.AbstractBaseDao;
import com.iswAcademy.Voucherz.dao.ITokenDao;
import com.iswAcademy.Voucherz.domain.Page;
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
        find = new SimpleJdbcCall(dataSource).withProcedureName("uspFindUserByToken3").returningResultSet(SINGLE_RESULT,new BeanPropertyRowMapper<>(PasswordResetToken.class));
        findUserEmailByToken = new SimpleJdbcCall(dataSource).withProcedureName("uspFindUserEmail").returningResultSet(SINGLE_RESULT,new BeanPropertyRowMapper<>(User.class));
        findUser = new SimpleJdbcCall(dataSource).withProcedureName("uspFindUserByToken").returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(PasswordResetToken.class));
    }



    @Override
    public PasswordResetToken find(String token) {
        SqlParameterSource in = new MapSqlParameterSource().addValue("token", token);
        Map<String,Object> m = find.execute(in);
        List<PasswordResetToken>list = (List<PasswordResetToken>) m.get(SINGLE_RESULT);
        if(list==null || list.isEmpty()){
            return null;
        }
        return list.get(0);
    }



    @Override
    public PasswordResetToken findById(long id) {
        return null;
    }


}
