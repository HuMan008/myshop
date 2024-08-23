package com.syj.myshop.auth.mapper;

import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import com.syj.myshop.auth.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author SuYajiang
 * @email suyajiang@aoscript.com
 * @create 2024-08-21 13:46
 */
@Mapper
public interface UserMapper {
    @Select("select * from sys_user where username = #{username}")
    SysUser getByUsername(@Param("username") String username);

}
