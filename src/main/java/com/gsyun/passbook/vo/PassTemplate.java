package com.gsyun.passbook.vo;

import com.gsyun.passbook.constant.ErrorCode;
import com.gsyun.passbook.dao.MerchantsDao;
import com.gsyun.passbook.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author gongshiyun
 * @Description 投放的优惠券对象定义
 * @date 2018/7/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassTemplate {

    /**
     * 所属商户id
     */
    private Integer id;

    /**
     * 优惠券标题
     */
    private String title;

    /**
     * 优惠券摘要
     */
    private String summary;

    /**
     * 优惠券详细信息
     */
    private String desc;

    /**
     * 最大个数限制
     */
    private Long limit;

    /**
     * 优惠券是否有Token, 用于用户核销
     */
    private Boolean hasToken;

    /**
     * 优惠券背景色
     */
    private Integer background;

    /**
     * 优惠券开始时间
     */
    private Data start;

    /**
     * 优惠券结束时间
     */
    private Date end;

    private ErrorCode validate(MerchantsDao merchantsDao) {
        if (null == merchantsDao.findById(id)) {
            return ErrorCode.MERCHANTS_NOT_EXITS;
        }

        return ErrorCode.SUCCESS;
    }
}