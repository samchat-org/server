package com.samchat.common.beans.auto.db.mapper;

import com.samchat.common.beans.auto.db.entitybeans.TSysMessageTemplete;
import com.samchat.common.beans.auto.db.entitybeans.TSysMessageTempleteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSysMessageTempleteMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_message_templete
     *
     * @mbggenerated
     */
    int countByExample(TSysMessageTempleteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_message_templete
     *
     * @mbggenerated
     */
    int deleteByExample(TSysMessageTempleteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_message_templete
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_message_templete
     *
     * @mbggenerated
     */
    int insert(TSysMessageTemplete record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_message_templete
     *
     * @mbggenerated
     */
    int insertSelective(TSysMessageTemplete record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_message_templete
     *
     * @mbggenerated
     */
    List<TSysMessageTemplete> selectByExample(TSysMessageTempleteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_message_templete
     *
     * @mbggenerated
     */
    TSysMessageTemplete selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_message_templete
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TSysMessageTemplete record, @Param("example") TSysMessageTempleteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_message_templete
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TSysMessageTemplete record, @Param("example") TSysMessageTempleteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_message_templete
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TSysMessageTemplete record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_message_templete
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TSysMessageTemplete record);
}