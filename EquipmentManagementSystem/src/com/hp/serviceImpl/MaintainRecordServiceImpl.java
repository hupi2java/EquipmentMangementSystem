package com.hp.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hp.base.service.BaseService;
import com.hp.domain.MaintainRecord;
import com.hp.domain.Users;
import com.hp.serviceInter.MaintainRecordServiceInter;
import com.hp.utils.DateManage;

@Service
public class MaintainRecordServiceImpl extends BaseService implements
		MaintainRecordServiceInter {
	
	//查询保养结果
	private String sql="select m.firstResult,m.secResult,ma.selection from MaintainRecord m "+
			"join equipment e "+
			"on m.e_id=e.id and e.id=? "+
			"join maintainItems ma "+
			"on m.m_id=ma.id and ma.id=? "+
			"where  m.maintaintime>=? and m.maintaintime<=? "+
			"order by m.maintaintime DESC";
	
	//flag ==0 查询保养者  
	private String sql1="select u.name from MaintainRecord m "+
			"join users u " +
			"on u.id=m.u_id "+
			"join equipment e "+
			"on m.e_id=e.id and e.id=? "+
			"join maintainItems ma "+
			"on m.m_id=ma.id and ma.id=? "+
			"where  m.maintaintime>=? and m.maintaintime<=? "+
			"order by m.maintaintime DESC";
	//flag==1 查询确认者
	private String sql2="select u.name from MaintainRecord m "+
			"join users u " +
			"on u.id=m.en_id "+
			"join equipment e "+
			"on m.e_id=e.id and e.id=? "+
			"join maintainItems ma "+
			"on m.m_id=ma.id and ma.id=? "+
			"where  m.maintaintime>=? and m.maintaintime<=? "+
			"order by m.maintaintime DESC";
	private int[] monthcount={31,0,31,30,31,30,31,31,30,31,30,31};
	
	//获取最近一次的记录时间
	public Timestamp getLastTime(String eid,String type){
		String sqlOfTime=" select top 1 maintaintime from MaintainRecord "+
	" join equipment "+
		" on MaintainRecord.e_id=equipment.id and equipment.eid=? "+
	" join machine "+
		" on equipment.m_id=machine.id "+
	" join maintainItems "+
		" on machine.id=maintainItems.m_id and maintainRecord.m_id=maintainItems.id "+
	" join datecycle "+
		" on maintainItems.d_id=datecycle.id and datecycle.type=? "+
		" order by maintaintime DESC ";
		String[] parameters={eid,type};
		List list=getResultBySQL(sqlOfTime, parameters);
		if(list!=null && list.size()==1){
			return (Timestamp) list.get(0);
		}
		
		return null;
	}
	
	//获取 保养记录的相关信息
	public List<Object[]> getConfirmObject(int year,int month,String datetype,Users user){
		//保养周期为空 
		if(datetype==null){
			return null;
		}else{	
			String startTime;
			String endTime;
			if("day".equals(datetype)||"week".equals(datetype)){//保养周期是week or day
				//如果是闰年
				if((year%4==0&&year%100!=0)||(year%400==0)){
					monthcount[1]=29;
				}else{
					monthcount[1]=28;
				}
				startTime = year+"-"+month+"-1 0:0:0";//例如 2015-11-0 0:0:0;
				endTime = year+"-"+month+"-"+monthcount[month-1]+" 23:59:59.999";//2015-11-30 23:59:59.999
			}else{//其他保养周期
				startTime = year+"-1-1 0:0:0";
				endTime = year+"-12-31 23:59:59.999";
			}
			//获取 确认保养者的信息，用于查询 该确认保养者能查询到的保养信息，
			//列：该user属于department.id=1的部门，因此该user自能查询出department.id=1的部门下的设备保养信息
			String hqlOfUser = "from Users where jobId=?";
			String[] parameters = {user.getJobId()};
			List<Users> list = getResult(hqlOfUser, parameters); 
		
			//查询出 保养记录ID，设备条码，机器类型，机器型号，保养周期，线别名，保养时间，保养项目，标准，保养类型0？1，保养结果，保养者工号
			String sqlOfRecord = "select maintainRecord.id,equipment.eid,machine.type,machine.version," +
					"line.name,maintainRecord.maintaintime,maintainItems.project,maintainItems.norm,maintainItems.selection," +
					"maintainRecord.firstResult,maintainRecord.secResult,u1.jobId from maintainRecord "+
					"join department "+
					"on department.id=? "+
				"join line "+
					"on line.d_id = department.id "+
				"join equipment "+
					"on equipment.l_id = line.id and maintainRecord.e_id=equipment.id "+
				"join machine "+
					"on equipment.m_id = machine.id "+
				"join maintainItems "+
					"on maintainRecord.m_id=maintainItems.id "+
				"join datecycle "+
					"on maintainItems.d_id=datecycle.id and datecycle.type=? "+
				"join users as u1 "+
					"on u1.id=maintainRecord.u_id "+
				"where maintainRecord.en_id is null and maintainRecord.maintaintime>=? and maintainRecord.maintaintime<=? "+
					"order by maintainRecord.maintaintime ";
			String[] param = {list.get(0).getDepartment().getId().toString(),datetype,startTime,endTime};
			List<Object[]> listObject=getResultBySQL(sqlOfRecord, param);
			return listObject;
		}
	}
	
	//获取日保养记录
	public List<String> getDayRecord(Integer equipmentId,Integer maintainItemsId,Integer year,Integer month){
		//如果是闰年
		if((year%4==0&&year%100!=0)||(year%400==0)){
			monthcount[1]=29;
		}else{
			monthcount[1]=28;
		}
		String startTime;
		String endTime;
		List<String> dayRecord=new ArrayList<String>();
		for (int i=0;i<monthcount[month-1];i++){
			startTime= year+"-"+month+"-"+(i+1)+" 0:0:0";
			endTime = year+"-"+month+"-"+(i+1)+" 23:59:59.999";
			Object[] parameters = {equipmentId,maintainItemsId,startTime,endTime};
			List<Object[]> list=getResultBySQL(sql, parameters);
			if(list!=null&&list.size()>=1){
				Object[] obj = list.get(0);
				if("0".equals(obj[2].toString())){ 
					dayRecord.add(obj[0].toString());
				}else if("1".equals(obj[2].toString())){
					dayRecord.add(obj[1].toString());
				}else{
					dayRecord.add(null);
				}	
			}else{
				dayRecord.add(null);	
			}
		}

		return dayRecord;
	}
	
	/**
	 * @return 获取日保养人信息 。
	 * @param flag   0 表示 获取 保养人信息  。 1 表示 获取 确认人信息
	 */
	public List<String> getMaintenancePersonByDay(Integer equipmentId,Integer maintainItemsId,Integer year,Integer month,int flag){

		//如果是闰年
		if((year%4==0&&year%100!=0)||(year%400==0)){
			monthcount[1]=29;
		}else{
			monthcount[1]=28;
		}
	
		String startTime;
		String endTime;
		List<String> maintenancePerson=new ArrayList<String>();
		for (int i=0;i<monthcount[month-1];i++){
			startTime= year+"-"+month+"-"+(i+1)+" 0:0:0";
			endTime = year+"-"+month+"-"+(i+1)+" 23:59:59.999";
			Object[] parameters = {equipmentId,maintainItemsId,startTime,endTime};
			List<Object> list = new ArrayList<Object>();
			if(flag==0){
				list=getResultBySQL(sql1, parameters);
			}else if(flag==1){
				list=getResultBySQL(sql2, parameters);
			}			
			if(list!=null&&list.size()>=1){
				maintenancePerson.add(list.get(0).toString());
			}else{
				maintenancePerson.add(null);
			}
		
		}
		return maintenancePerson;
	}
	
	public List<String> getWeekRecord(Integer equipmentId,Integer maintainItemsId,Integer year,Integer month){

		//如果是闰年
		if((year%4==0&&year%100!=0)||(year%400==0)){
			monthcount[1]=29;
		}else{
			monthcount[1]=28;
		}

		String[] startTime = new String[5];//存储每周的开始时间
		String[] endTime = new String[5];//存储每周的结束时间
		int firstDayOfMonth = DateManage.getWeekIs(year, month);//某年 某月的第一天是周几
		startTime[0] = year+"-"+month+"-1 0:0:0";
		switch(firstDayOfMonth){
		case 0 : endTime[0]= year+"-"+month+"-"+7+" 23:59:59.999";break;
		case 1 : endTime[0]= year+"-"+month+"-"+6+" 23:59:59.999";break;
		case 2 : endTime[0]= year+"-"+month+"-"+5+" 23:59:59.999";break;
		case 3 : endTime[0]= year+"-"+month+"-"+4+" 23:59:59.999";break;
		case 4 : endTime[0]= year+"-"+month+"-"+3+" 23:59:59.999";break;
		case 5 : endTime[0]= year+"-"+month+"-"+2+" 23:59:59.999";break;
		case 6 : endTime[0]= year+"-"+month+"-"+1+" 23:59:59.999";break;
		}
		for(int i=1;i<5;i++){
			startTime[i] = endTime[i-1];
			if(7*(i+1)-firstDayOfMonth <= monthcount[month-1]){	
				endTime[i] = year+"-"+month+"-"+(7*(i+1)-firstDayOfMonth)+" 23:59:59.999";
			}else{
				endTime[i] = year+"-"+month+"-"+monthcount[month-1]+" 23:59:59.999";
			}
		}
		List<String> weekRecord=new ArrayList<String>();
		for(int i=0;i<5;i++){
			Object[] parameters = {equipmentId,maintainItemsId,startTime[i],endTime[i]};
			List<Object[]> list= getResultBySQL(sql, parameters);
			if(list!=null&&list.size()>=1){
				Object[] obj = list.get(0);
				if("0".equals(obj[2].toString())){
					weekRecord.add(obj[0].toString());
				}else if("1".equals(obj[2].toString())){
					weekRecord.add(obj[1].toString());
				}else{
					weekRecord.add(null);
				}
			}else{
				weekRecord.add(null);
			}
		}
		
		return  weekRecord;
	}
	
	/**
	 * 
	 * @param equipmentId
	 * @param maintainItemsId
	 * @param year
	 * @param month
	 * @param flag  0  返回 保养者 。  1 返回 确认者
	 * @return  周保养者/确认者 信息
	 */
	public List<String> getMaintenancePersonByWeek(Integer equipmentId,Integer maintainItemsId,Integer year,Integer month,int flag){
	
		//如果是闰年
		if((year%4==0&&year%100!=0)||(year%400==0)){
			monthcount[1]=29;
		}else{
			monthcount[1]=28;
		}

		String[] startTime = new String[5];//存储每周的开始时间
		String[] endTime = new String[5];//存储每周的结束时间
		int firstDayOfMonth = DateManage.getWeekIs(year, month);//某年 某月的第一天是周几
		startTime[0] = year+"-"+month+"-1 0:0:0";
		switch(firstDayOfMonth){
		case 0 : endTime[0]= year+"-"+month+"-"+7+" 23:59:59.999";break;
		case 1 : endTime[0]= year+"-"+month+"-"+6+" 23:59:59.999";break;
		case 2 : endTime[0]= year+"-"+month+"-"+5+" 23:59:59.999";break;
		case 3 : endTime[0]= year+"-"+month+"-"+4+" 23:59:59.999";break;
		case 4 : endTime[0]= year+"-"+month+"-"+3+" 23:59:59.999";break;
		case 5 : endTime[0]= year+"-"+month+"-"+2+" 23:59:59.999";break;
		case 6 : endTime[0]= year+"-"+month+"-"+1+" 23:59:59.999";break;
		}
		for(int i=1;i<5;i++){
			startTime[i] = endTime[i-1];
			if(7*(i+1)-firstDayOfMonth <= monthcount[month-1]){	
				endTime[i] = year+"-"+month+"-"+(7*(i+1)-firstDayOfMonth)+" 23:59:59.999";
			}else{
				endTime[i] = year+"-"+month+"-"+monthcount[month-1]+" 23:59:59.999";
			}
		}
		
		List<String> maintenancePerson = new ArrayList<String>();
		for(int i=0;i<5;i++){
			Object[] parameters = {equipmentId,maintainItemsId,startTime[i],endTime[i]};
			List<Object> list = new ArrayList<Object>();
			if(flag==0){
				list = getResultBySQL(sql1, parameters);
			}else if(flag==1){
				list = getResultBySQL(sql2, parameters);
			}
			if(list!=null&&list.size()>=1){
				maintenancePerson.add(list.get(0).toString());
			}else{
				maintenancePerson.add(null);
			}
		}
		
		return maintenancePerson;
	}	
	
	public List<String> getMonthRecord(Integer equipmentId,Integer maintainItemsId,Integer year){

		//如果是闰年
		if((year%4==0&&year%100!=0)||(year%400==0)){
			monthcount[1]=29;
		}else{
			monthcount[1]=28;
		}

		String startTime;
		String endTime;
		List<String> monthRecord = new ArrayList<String>();
		for(int i=0;i<12;i++){
			startTime=year+"-"+(i+1)+"-1 0:0:0";
			endTime=year+"-"+(i+1)+"-"+monthcount[i]+" 23:59:59.999";
			Object[] parameters = {equipmentId,maintainItemsId,startTime,endTime};
			List<Object[]> list = getResultBySQL(sql, parameters);
			if(list!=null&&list.size()>=1){
				Object[] obj = list.get(0);
				if("0".equals(obj[2].toString())){
					monthRecord.add(obj[0].toString());
				}else if("1".equals(obj[2].toString())){
					monthRecord.add(obj[1].toString());
				}else{
					monthRecord.add(null);
				}
			}else{
				monthRecord.add(null);
			}
		}
		
		return monthRecord;
	}
	
	public List<String> getMaintenancePersonByMonth(Integer equipmentId,Integer maintainItemsId,Integer year,Integer month,int flag){
		
		//如果是闰年
		if((year%4==0&&year%100!=0)||(year%400==0)){
			monthcount[1]=29;
		}else{
			monthcount[1]=28;
		}

		String startTime;
		String endTime;
		List<String> maintaincePerson = new ArrayList<String>();
		for(int i=0;i<12;i++){
			startTime=year+"-"+(i+1)+"-1 0:0:0";
			endTime=year+"-"+(i+1)+"-"+monthcount[i]+" 23:59:59.999";
			Object[] parameters = {equipmentId,maintainItemsId,startTime,endTime};
			List<Object> list = new ArrayList<Object>();
			if(flag==0){
				list = getResultBySQL(sql1, parameters);
			}else if(flag==1){
				list = getResultBySQL(sql2, parameters);
			}
			if(list!=null&&list.size()>=1){
				maintaincePerson.add(list.get(0).toString());
			}else{
				maintaincePerson.add(null);
			}
		}
		return maintaincePerson;
	}
	
	
	public List<String> getQuarterRecord(Integer equipmentId,Integer maintainItemsId,Integer year){

		String[] startTime = {year+"-1-1 0:0:0",year+"-4-1 0:0:0",year+"-7-1 0:0:0",year+"-10-1 0:0:0"};
		String[] endTime = {year+"-3-31 23:59:59.999",year+"-6-30 23:59:59.999",year+"-9-30 23:59:59.999",year+"-12-31 23:59:59.999"};
		List<String> quarterRecord = new ArrayList<String>();
		for(int i=0;i<4;i++){
			Object[] parameters = {equipmentId,maintainItemsId,startTime[i],endTime[i]};
			List<Object[]> list = getResultBySQL(sql, parameters);
			if(list!=null&&list.size()>=1){
				Object[] obj = list.get(0);
				if("0".equals(obj[2].toString())){
					quarterRecord.add(obj[0].toString());
				}else if("1".equals(obj[2].toString())){
					quarterRecord.add(obj[1].toString());
				}else{
					quarterRecord.add(null);
				}
			}else{
				quarterRecord.add(null);
			}
		}
		
		return quarterRecord;
	}
	
	
public List<String> getMaintenancePersonByQuarter(Integer equipmentId,Integer maintainItemsId,Integer year,Integer month,int flag){

	String[] startTime = {year+"-1-1 0:0:0",year+"-4-1 0:0:0",year+"-7-1 0:0:0",year+"-10-1 0:0:0"};
	String[] endTime = {year+"-3-31 23:59:59.999",year+"-6-30 23:59:59.999",year+"-9-30 23:59:59.999",year+"-12-31 23:59:59.999"};
		List<String> maintaincePerson = new ArrayList<String>();
		for(int i=0;i<4;i++){
			Object[] parameters = {equipmentId,maintainItemsId,startTime[i],endTime[i]};
			List<Object> list = new ArrayList<Object>();
			if(flag==0){
				list = getResultBySQL(sql1, parameters);
			}else if(flag==1){
				list = getResultBySQL(sql2, parameters);
			}
			if(list!=null&&list.size()>=1){
				maintaincePerson.add(list.get(0).toString());
			}else{
				maintaincePerson.add(null);
			}
		}
		return maintaincePerson;
	}

	public List<String> getHalfYearRecord(Integer equipmentId,Integer maintainItemsId,Integer year){
	
		String[] startTime = {year+"-1-1 0:0:0",year+"-7-1 0:0:0"};
		String[] endTime = {year+"-6-30 23:59:59.999",year+"-12-31 23:59:59.999"};
		List<String> halfYearRecord = new ArrayList<String>();
		for(int i=0;i<2;i++){
			Object[] parameters = {equipmentId,maintainItemsId,startTime[i],endTime[i]};
			List<Object[]> list = getResultBySQL(sql, parameters);
			if(list!=null&&list.size()>=1){
				Object[] obj = list.get(0);
				if("0".equals(obj[2].toString())){
					halfYearRecord.add(obj[0].toString());
				}else if("1".equals(obj[2].toString())){
					halfYearRecord.add(obj[1].toString());
				}else{
					halfYearRecord.add(null);
				}
			}else{
				halfYearRecord.add(null);
			}
		}
		
		return halfYearRecord;
	}
	
	
	public List<String> getMaintenancePersonByHalfYear(Integer equipmentId,Integer maintainItemsId,Integer year,Integer month,int flag){
	
		String[] startTime = {year+"-1-1 0:0:0",year+"-7-1 0:0:0"};
		String[] endTime = {year+"-6-30 23:59:59.999",year+"-12-31 23:59:59.999"};
		List<String> maintaincePerson = new ArrayList<String>();
		for(int i=0;i<2;i++){
			Object[] parameters = {equipmentId,maintainItemsId,startTime[i],endTime[i]};
			List<Object> list = new ArrayList<Object>();
			if(flag==0){
				list = getResultBySQL(sql1, parameters);
			}else if(flag==1){
				list = getResultBySQL(sql2, parameters);
			}
			if(list!=null&&list.size()>=1){
				maintaincePerson.add(list.get(0).toString());
			}else{
				maintaincePerson.add(null);
			}
		}
		return maintaincePerson;
	}
	
	public List<String> getYearRecord(Integer equipmentId,Integer maintainItemsId,Integer year){
		
		String startTime = year+"-1-1 0:0:0";
		String endTime = year+"-12-31 23:59:59.999";
		List<String> halfYearRecord = new ArrayList<String>();

		Object[] parameters = {equipmentId,maintainItemsId,startTime,endTime};
		List<Object[]> list = getResultBySQL(sql, parameters);
		if(list!=null&&list.size()>=1){
			Object[] obj = list.get(0);
			if("0".equals(obj[2].toString())){
				halfYearRecord.add(obj[0].toString());
			}else if("1".equals(obj[2].toString())){
				halfYearRecord.add(obj[1].toString());
			}else{
				halfYearRecord.add(null);
			}
		}else{
			halfYearRecord.add(null);
		}

		return halfYearRecord;
	}
	
	
	public List<String> getMaintenancePersonByYear(Integer equipmentId,Integer maintainItemsId,Integer year,Integer month,int flag){
	
		String startTime = year+"-1-1 0:0:0";
		String endTime = year+"-12-31 23:59:59.999";
		List<String> maintaincePerson = new ArrayList<String>();

		Object[] parameters = {equipmentId,maintainItemsId,startTime,endTime};
		List<Object> list = new ArrayList<Object>();
		if(flag==0){
			list = getResultBySQL(sql1, parameters);
		}else if(flag==1){
			list = getResultBySQL(sql2, parameters);
		}
		if(list!=null&&list.size()>=1){
			maintaincePerson.add(list.get(0).toString());
		}else{
			maintaincePerson.add(null);
		}
		return maintaincePerson;
	}

}
