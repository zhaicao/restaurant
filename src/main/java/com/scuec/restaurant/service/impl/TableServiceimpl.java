package com.scuec.restaurant.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.dao.TableDao;
import com.scuec.restaurant.entities.Table;
import com.scuec.restaurant.entities.User;
import com.scuec.restaurant.service.TableService;
import javafx.scene.control.Tab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableServiceimpl implements TableService {
    @Autowired
    private TableDao tableDao;

    @Override
    public int deleteTableById(String tableId) {

        return tableDao.updateTable(tableId,null,null,null,0,1);
    }

    @Override
    public Table getTableById(String tableId) {
        return tableDao.getTableById(tableId);
    }

    @Override
    public int updateTable(String tableId, String tableNo, String tPeople, String tOrderid) {
        return tableDao.updateTable(tableId, tableNo, tPeople, tOrderid, 0, 0);
    }

    @Override
    public IPage<User> getTableList(int currentPage, int pageSize,String tableId) {
        return tableDao.getTableList(new Page<>(currentPage, pageSize),tableId);
    }

    @Override
    public int addTable( String tableNo, String tPeople, String tOrderid) {
        return tableDao.insertTable(tableNo, tPeople, tOrderid);
    }

}
