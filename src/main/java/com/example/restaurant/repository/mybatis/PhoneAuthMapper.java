package com.example.restaurant.repository.mybatis;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

public interface EmailAuthMapper {

  int insert(@Param("email") String email,   @Param("auth") String auth);

  int deleteExpiredAuthCodes(@Param("email") String email, @Param("auth") String auth);


  int emailcount(@Param("email") String email);


  int deleteauth(@Param("email") String email);

  int findAuthCodeByEmail(HashMap<String,Object> map);


}
