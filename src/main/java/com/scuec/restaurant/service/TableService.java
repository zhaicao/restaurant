package com.scuec.restaurant.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scuec.restaurant.entities.Table;
import javafx.scene.control.Tab;

public interface TableService {
    /**
     * 逻辑删除桌子，isdel设置为1
     * @param tableId 桌号Id
     * @return
     */
    int deleteTableById(String tableId);

    Table getTableById(String tableId);

    int updateTable(String tableId, String tableNo, String tPeople, String tOrderid);

    IPage<Table> getTableList(int currentPage, int pageSize, String tableId,String tableNo,int tableUse);

    Table addTable(Table table);

    int updateTableUse(String tableId,String tableOrderId);

    int updateTableUse1(String tableId);
}
