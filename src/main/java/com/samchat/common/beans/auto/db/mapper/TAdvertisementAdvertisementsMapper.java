package com.samchat.common.beans.auto.db.mapper;

import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementAdvertisements;
import com.samchat.common.beans.auto.db.entitybeans.TAdvertisementAdvertisementsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAdvertisementAdvertisementsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_advertisement_advertisements
     *
     * @mbggenerated
     */
    int countByExample(TAdvertisementAdvertisementsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_advertisement_advertisements
     *
     * @mbggenerated
     */
    int deleteByExample(TAdvertisementAdvertisementsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_advertisement_advertisements
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long ads_id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_advertisement_advertisements
     *
     * @mbggenerated
     */
    int insert(TAdvertisementAdvertisements record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_advertisement_advertisements
     *
     * @mbggenerated
     */
    int insertSelective(TAdvertisementAdvertisements record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_advertisement_advertisements
     *
     * @mbggenerated
     */
    List<TAdvertisementAdvertisements> selectByExample(TAdvertisementAdvertisementsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_advertisement_advertisements
     *
     * @mbggenerated
     */
    TAdvertisementAdvertisements selectByPrimaryKey(Long ads_id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_advertisement_advertisements
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TAdvertisementAdvertisements record, @Param("example") TAdvertisementAdvertisementsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_advertisement_advertisements
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TAdvertisementAdvertisements record, @Param("example") TAdvertisementAdvertisementsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_advertisement_advertisements
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TAdvertisementAdvertisements record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_advertisement_advertisements
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TAdvertisementAdvertisements record);
}