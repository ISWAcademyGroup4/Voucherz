package com.iswAcademy.Voucherz.dao.impl;

import com.iswAcademy.Voucherz.dao.AbstractBaseDao;
import com.iswAcademy.Voucherz.dao.IActivationTokenDao;
import com.iswAcademy.Voucherz.domain.ActivationToken;
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
public class ActivationTokenDao extends AbstractBaseDao<ActivationToken> implements IActivationTokenDao {

    @Autowired
    @Override
    public void setDataSource(@Qualifier(value="dataSource") DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        create = new SimpleJdbcCall(dataSource).withProcedureName("uspCreateActivationToken").withReturnValue();
        update = new SimpleJdbcCall(dataSource).withProcedureName("uspUserUpdate").withReturnValue();
        find = new SimpleJdbcCall(dataSource).withProcedureName("uspFindUserByToken3").returningResultSet(SINGLE_RESULT,new BeanPropertyRowMapper<>(ActivationToken.class));
    }

    @Override
    public ActivationToken find(String token) {
        SqlParameterSource in = new MapSqlParameterSource().addValue("token", token);
        Map<String,Object> m = find.execute(in);
        List<ActivationToken> list = (List<ActivationToken>) m.get(SINGLE_RESULT);
        if(list==null || list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public ActivationToken findById(long id) {
        return null;
    }

    @Override
    public Page<ActivationToken> findAll() {
        return null;
    }
}
