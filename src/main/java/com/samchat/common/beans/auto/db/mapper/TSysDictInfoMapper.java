package com.samchat.common.beans.auto.db.mapper;

import com.samchat.common.beans.auto.db.entitybeans.TSysDictInfo;
import com.samchat.common.beans.auto.db.entitybeans.TSysDictInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSysDictInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_dict_info
     *
     * @mbggenerated
     */
    int countByExample(TSysDictInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_dict_info
     *
     * @mbggenerated
     */
    int deleteByExample(TSysDictInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_dict_info
     *
     * @mbggenerated
     */
    int insert(TSysDictInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_dict_info
     *
     * @mbggenerated
     */
    int insertSelective(TSysDictInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_dict_info
     *
     * @mbggenerated
     */
    List<TSysDictInfo> selectByExample(TSysDictInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_dict_info
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TSysDictInfo record, @Param("example") TSysDictInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_dict_info
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TSysDictInfo record, @Param("example") TSysDictInfoExample example);
}