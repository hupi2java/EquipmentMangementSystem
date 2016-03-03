package com.hp.serviceInter;

import java.sql.Timestamp;
import java.util.List;

import com.hp.base.service.BaseServiceInter;
import com.hp.domain.Users;

public interface MaintainRecordServiceInter extends BaseServiceInter {
	//获取最后一次保养时间
	public Timestamp getLastTime(String eid,String type);
	//获取保养确认的信息
	public List<Object[]> getConfirmObject(int year,int month,String datetype,Users user);
	//获取 日保养记录信息
	public List<String> getDayRecord(Integer equipmentId,Integer maintainItemsId,Integer year,Integer month);
	//获取 日保养 保养人及保养确认人信息  flag==0 返回保养人信息，flag==1返回确认人信息
	public List<String> getMaintenancePersonByDay(Integer equipmentId,Integer maintainItemsId,Integer year,Integer month,int flag);
	//获取 周保养记录信息
	public List<String> getWeekRecord(Integer equipmentId,Integer maintainItemsId,Integer year,Integer month);
	//获取 周保养 保养人及保养确认人信息  flag==0 返回保养人信息，flag==1返回确认人信息
	public List<String> getMaintenancePersonByWeek(Integer equipmentId,Integer maintainItemsId,Integer year,Integer month,int flag);
	//获取 月保养记录信息
	public List<String> getMonthRecord(Integer equipmentId,Integer maintainItemsId,Integer year);
	//获取 月保养 保养人及保养确认人信息  flag==0 返回保养人信息，flag==1返回确认人信息
	public List<String> getMaintenancePersonByMonth(Integer equipmentId,Integer maintainItemsId,Integer year,Integer month,int flag);
	//获取 季保养记录信息
	public List<String> getQuarterRecord(Integer equipmentId,Integer maintainItemsId,Integer year);
	//获取 季保养 保养人及保养确认人信息  flag==0 返回保养人信息，flag==1返回确认人信息
	public List<String> getMaintenancePersonByQuarter(Integer equipmentId,Integer maintainItemsId,Integer year,Integer month,int flag);
	//
	public List<String> getHalfYearRecord(Integer equipmentId,Integer maintainItemsId,Integer year);
	//
	public List<String> getMaintenancePersonByHalfYear(Integer equipmentId,Integer maintainItemsId,Integer year,Integer month,int flag);
	//
	public List<String> getYearRecord(Integer equipmentId,Integer maintainItemsId,Integer year);
	//
	public List<String> getMaintenancePersonByYear(Integer equipmentId,Integer maintainItemsId,Integer year,Integer month,int flag);

	
}
