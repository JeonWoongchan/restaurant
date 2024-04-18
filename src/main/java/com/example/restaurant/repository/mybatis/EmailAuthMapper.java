package com.example.restaurant.repository.mybatis;

import org.apache.ibatis.annotations.Param;

public interface EmailAuth {

  int insert(@Param("email") String email,   @Param("auth") String auth);

  int deleteExpiredAuthCodes(@Param("email") String email, @Param("auth") String auth,long expirationTime);



}
