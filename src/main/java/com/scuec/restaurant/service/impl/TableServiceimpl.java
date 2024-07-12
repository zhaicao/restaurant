package com.scuec.restaurant.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;
import com.scuec.restaurant.dao.TableDao;
import com.scuec.restaurant.entities.Table;
import com.scuec.restaurant.entities.vo.FoodVO;
import com.scuec.restaurant.entities.vo.TableVO;
import com.scuec.restaurant.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableServiceimpl implements TableService {
    @Autowired
    private TableDao tableDao;

    @Autowired
    private Table table;

    @Override
    public int deleteTableById(String tableId) {

        return tableDao.deleteTableById(tableId);
    }

    @Override
    public Table getTableById(String tableId) {
        return tableDao.getTableById(tableId);
    }

    @Override
    public Table updateTable(Table request) {
        /*String tableId, String tableNo, String tPeople, String tOrderid*/
        if (tableDao.getTableByName(request.getTableName()) == null) {
            int res = tableDao.updateTable(request);
            if (res == 1)
                return request;
            else
                return null;
        } else
            // 桌位名存在抛出异常
            throw new GlobalException(ResponseCode.ERROR, "TableName already exist");

    }

    @Override
    public IPage<Table> getTableList(int currentPage, int pageSize, String tableId,String tableName,int tableUse) {
        return tableDao.getTableList(new Page<>(currentPage, pageSize),tableId, tableName, tableUse);
    }

    @Override
    public Table addTable(Table request) {
        // 判断桌位名是否存在
        if (tableDao.getTableByName(request.getTableName()) == null) {
            table.setTableName(request.getTableName());
            table.setTableDescription(request.getTableDescription());
            table.setTableId(null);
            table.setTableUse(0);
            table.setTableDel(0);
            int res = tableDao.insertTable(table);
            if (res == 1) {
                return table;
            } else
                return null;
        } else
            // 桌位名存在抛出异常
            throw new GlobalException(ResponseCode.ERROR, "TableName already exist");

    }

    @Override
    public int updateTableUse(String tableId, String tableOrderId) {
        Table table = tableDao.getTableById(tableId);
        int tableUse = table.getTableUse();
        if(tableUse == 0) {
            return tableDao.updateTableUse(tableId, tableOrderId);
        }
        else {
            return 0;
        }
    }

    @Override
    public int updateTableUse1(String tableId) {
        return tableDao.updateTableUse1(tableId);
    }

    @Override
    public List<TableVO> getTableListAll() {

        List<TableVO> tables = tableDao.getTableListAll();
        return tables;
    }

    @Override
    public Integer getTablestaById(String tableId) {
        return tableDao.getTablestaById(tableId);
    }

}
