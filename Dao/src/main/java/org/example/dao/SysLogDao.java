package org.example.dao;

import org.example.daomain.SysLog;

import java.util.List;

public interface SysLogDao {

    public void save(SysLog sysLog);

    public List<SysLog> findAll();
}
