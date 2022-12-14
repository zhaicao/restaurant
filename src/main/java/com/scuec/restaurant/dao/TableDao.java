package com.scuec.restaurant.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.entities.Table;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TableDao extends BaseMapper<Table> {


    int insertTable(Table table);

    /**
     * 修改桌号信息
     * @param tableId 必填
     * @param tableName 非必填
     * @param tableDescription 非必填
     * @param tableOrderId 非必填
     * @param tableUse 非必填
     * @param tableDel 非必填
     * @return
     */
    int updateTable(Table table);

    Table getTableById(String tableId);

    /**
     * 通过桌号名查询桌位
     * @param tableName 桌号名
     * @return
     */
    Table getTableByName(String tableName);

    IPage<Table> getTableList(@Param("page") Page<Table> page,
                              String tableId,
                              String tableName,
                              int tableUse);

    int updateTableUse(String tableId,String tableOrderId);

    int updateTableUse1(String tableId);

    int deleteTableById(String tableId);
}
