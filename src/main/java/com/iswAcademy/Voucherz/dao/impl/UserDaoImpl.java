package com.iswAcademy.Voucherz.dao.impl;

import com.iswAcademy.Voucherz.dao.AbstractBaseDao;
import com.iswAcademy.Voucherz.dao.IUserDao;
import com.iswAcademy.Voucherz.dao.util.RowCountMapper;
import com.iswAcademy.Voucherz.domain.Page;
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
public class UserDaoImpl extends AbstractBaseDao<User> implements IUserDao {
    protected SimpleJdbcCall findByEmail;

    @Autowired
    @Override
    public void setDataSource(@Qualifier(value="dataSource") DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        create = new SimpleJdbcCall(dataSource).withProcedureName("uspCreateUser2").withReturnValue();
        update = new SimpleJdbcCall(dataSource).withProcedureName("uspUserUpdate").withReturnValue();
//        findAll = new SimpleJdbcCall(dataSource).withProcedureName("")
        updatePassword = new SimpleJdbcCall(dataSource).withProcedureName("uspUpdateUserPassword").withReturnValue();
        find = new SimpleJdbcCall(dataSource).withProcedureName("uspFindUser").returningResultSet(SINGLE_RESULT,new BeanPropertyRowMapper<>(User.class));
        findById = new SimpleJdbcCall(dataSource).withProcedureName("uspFindById").returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(User.class));
        findUserByToken = new SimpleJdbcCall(dataSource).withProcedureName("uspFindUserByToken3").returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User findByEmail(String Email) {
        SqlParameterSource in = new MapSqlParameterSource().addValue("email", Email);
        Map<String,Object> m = find.execute(in);
        List<User>list = (List<User>) m.get(SINGLE_RESULT);
        if(list==null || list.isEmpty()){
            return null;
        }
        return list.get(0);
    }


    @Override
    public User find(String email) {
        SqlParameterSource in = new MapSqlParameterSource().addValue("email", email);
        Map<String,Object> m = find.execute(in);
        List<User>list = (List<User>) m.get(SINGLE_RESULT);
        if(list==null || list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public User findById(long id) {
        SqlParameterSource in = new MapSqlParameterSource().addValue("id", id);
        Map<String,Object> m = findById.execute(in);
        List<User>list = (List<User>) m.get(SINGLE_RESULT);
        if(list==null || list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public Page<User> findAll() {
        return null;
    }

}

