package com.skyworld.beans.auto.db.mapper;

import com.skyworld.beans.auto.db.entitybeans.TUserProUsers;
import com.skyworld.beans.auto.db.entitybeans.TUserProUsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserProUsersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_pro_users
     *
     * @mbggenerated
     */
    int countByExample(TUserProUsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_pro_users
     *
     * @mbggenerated
     */
    int deleteByExample(TUserProUsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_pro_users
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long pro_user_id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_pro_users
     *
     * @mbggenerated
     */
    int insert(TUserProUsers record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_pro_users
     *
     * @mbggenerated
     */
    int insertSelective(TUserProUsers record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_pro_users
     *
     * @mbggenerated
     */
    List<TUserProUsers> selectByExample(TUserProUsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_pro_users
     *
     * @mbggenerated
     */
    TUserProUsers selectByPrimaryKey(Long pro_user_id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_pro_users
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TUserProUsers record, @Param("example") TUserProUsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_pro_users
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TUserProUsers record, @Param("example") TUserProUsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_pro_users
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TUserProUsers record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_pro_users
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TUserProUsers record);
}