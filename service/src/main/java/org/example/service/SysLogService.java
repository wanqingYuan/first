package org.example.service;

import org.example.daomain.SysLog;

import java.util.List;

public interface SysLogService {
    public void save(SysLog sysLog);
    public List<SysLog> findAll();
}
