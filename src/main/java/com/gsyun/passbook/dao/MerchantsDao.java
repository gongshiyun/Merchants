package com.gsyun.passbook.dao;

import com.gsyun.passbook.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author gongshiyun
 * @Description Merchants Dao接口
 * @date 2018/7/25
 */
public interface MerchantsDao extends JpaRepository<Merchants,Integer> {

    /**
     * 根据id获取商户对象
     * @param id 商户id
     * @return
     */
    Optional<Merchants> findById(Integer id);

    /**
     * 根据商户名称获取商户对象
     * @param name 商户名称
     * @return
     */
    Merchants findByName(String name);
}
