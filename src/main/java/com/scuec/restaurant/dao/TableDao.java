package com.scuec.restaurant.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.entities.Table;
import com.scuec.restaurant.entities.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TableDao extends BaseMapper<Table> {

    /**
     * 新增桌号，TableId由mybatis自动生成
     * @param tableNo 桌号
     * @param tPeople 人数
     * @return
     */
    int insertTable(String tableNo,
                    String tPeople);


    int updateTable(String tableId,
                   String tableNo,
                   String tPeople,
                   String tOrderid,
                   int tableUse,
                   int tableDel);

    Table getTableById(String tableId);

    IPage<Table> getTableList(@Param("page") Page<Table> page,
                              String tableId,
                              String tableNo,
                              int tableUse);

    int updateTableuse(String tableId,String tOrderid);

    int updateTableuse1(String tableId);
}
