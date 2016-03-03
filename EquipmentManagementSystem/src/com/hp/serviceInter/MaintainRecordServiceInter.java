package com.hp.serviceInter;

import java.sql.Timestamp;
import java.util.List;

import com.hp.base.service.BaseServiceInter;
import com.hp.domain.Users;

public interface MaintainRecordServiceInter extends BaseServiceInter {
	//��ȡ���һ�α���ʱ��
	public Timestamp getLastTime(String eid,String type);
	//��ȡ����ȷ�ϵ���Ϣ
	public List<Object[]> getConfirmObject(int year,int month,String datetype,Users user);
	//��ȡ �ձ�����¼��Ϣ
	public List<String> getDayRecord(Integer equipmentId,Integer maintainItemsId,Integer year,Integer month);
	//��ȡ �ձ��� �����˼�����ȷ������Ϣ  flag==0 ���ر�������Ϣ��flag==1����ȷ������Ϣ
	public List<String> getMaintenancePersonByDay(Integer equipmentId,Integer maintainItemsId,Integer year,Integer month,int flag);
	//��ȡ �ܱ�����¼��Ϣ
	public List<String> getWeekRecord(Integer equipmentId,Integer maintainItemsId,Integer year,Integer month);
	//��ȡ �ܱ��� �����˼�����ȷ������Ϣ  flag==0 ���ر�������Ϣ��flag==1����ȷ������Ϣ
	public List<String> getMaintenancePersonByWeek(Integer equipmentId,Integer maintainItemsId,Integer year,Integer month,int flag);
	//��ȡ �±�����¼��Ϣ
	public List<String> getMonthRecord(Integer equipmentId,Integer maintainItemsId,Integer year);
	//��ȡ �±��� �����˼�����ȷ������Ϣ  flag==0 ���ر�������Ϣ��flag==1����ȷ������Ϣ
	public List<String> getMaintenancePersonByMonth(Integer equipmentId,Integer maintainItemsId,Integer year,Integer month,int flag);
	//��ȡ ��������¼��Ϣ
	public List<String> getQuarterRecord(Integer equipmentId,Integer maintainItemsId,Integer year);
	//��ȡ ������ �����˼�����ȷ������Ϣ  flag==0 ���ر�������Ϣ��flag==1����ȷ������Ϣ
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
