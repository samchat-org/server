package com.samchat.common.beans.auto.db.mapper;

import com.samchat.common.beans.auto.db.entitybeans.TContactProUser;
import com.samchat.common.beans.auto.db.entitybeans.TContactProUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TContactProUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contact_pro_user
     *
     * @mbggenerated
     */
    int countByExample(TContactProUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contact_pro_user
     *
     * @mbggenerated
     */
    int deleteByExample(TContactProUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contact_pro_user
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long contact_id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contact_pro_user
     *
     * @mbggenerated
     */
    int insert(TContactProUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contact_pro_user
     *
     * @mbggenerated
     */
    int insertSelective(TContactProUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contact_pro_user
     *
     * @mbggenerated
     */
    List<TContactProUser> selectByExample(TContactProUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contact_pro_user
     *
     * @mbggenerated
     */
    TContactProUser selectByPrimaryKey(Long contact_id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contact_pro_user
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TContactProUser record, @Param("example") TContactProUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contact_pro_user
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TContactProUser record, @Param("example") TContactProUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contact_pro_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TContactProUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contact_pro_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TContactProUser record);
}