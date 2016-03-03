package com.hp.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hp.domain.Equipment;
import com.hp.domain.MaintainItems;
import com.hp.serviceInter.EquipmentServiceInter;
import com.hp.serviceInter.MaintainItemsServiceInter;
import com.hp.serviceInter.MaintainRecordServiceInter;
import com.hp.utils.DateManage;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class QueryFormAction extends ActionSupport implements RequestAware{

	private String datetype;//保养类型
	private int year=-1;//获取保养确认时的年记录，没有赋值时为-1
	private int month=-1;//获取保养确认时的月记录
	private int equipmentId=0;
	private String equipmentEid;
	private String lineName;
	private String machineVersion=null;
	
	private Map<String,Object> request;
	@Resource
	private MaintainRecordServiceInter maintainRecordServiceInter;
	@Resource
	private EquipmentServiceInter equipmentServiceInter;
	@Resource
	private MaintainItemsServiceInter maintainItemsServiceInter;
	
	//跳转到查询报表UI
	public String goQueryUI(){
		request.put("type", "报表查询看板");
		return "goQueryUI";
	}
	
	//跳转到查询出的设备列UI
	public String goEquipmentListUI(){
		request.put("type", "报表查询看板");
		List<Equipment> equipmentList=equipmentServiceInter.getAll();
		request.put("equipmentList", equipmentList);
		return  "goEquipmentListUI";
	}
	
	//跳转到报表UI
	public String goFormUI(){
		List<MaintainItems> list = maintainItemsServiceInter.getMaintainItems(equipmentId, datetype);
		request.put("maintainItems", list);
		if("day".equals(datetype)){
			//获取每个项目的保养结果 dayRecord0  是第一个 保养项目 dayRecord1 是第2个保养项目 以此类推
			for(int i=0;i<list.size();i++){
				List<String> dayRecord = maintainRecordServiceInter.getDayRecord(equipmentId, list.get(i).getId(), year, month);
				request.put("dayRecord"+i, dayRecord);
				request.put("dayCount", dayRecord.size());//该月的天数
			}
			if(list!=null&&list.size()>0){
				//获取保养人信息
				List<String> maintancePerson = maintainRecordServiceInter.getMaintenancePersonByDay(equipmentId,list.get(0).getId(), year, month, 0);
				//获取 确认人 信息
				List<String> confirmPerson = maintainRecordServiceInter.getMaintenancePersonByDay(equipmentId,list.get(0).getId(), year, month, 1);
				request.put("maintancePerson", maintancePerson);
				request.put("confirmPerson", confirmPerson);
			}
			return "goDayFormUI";
		}else if("week".equals(datetype)){
			//获取每个项目的保养结果 weekRecord0  是第一个 保养项目 weekRecord1 是第2个保养项目 以此类推
			for(int i=0;i<list.size();i++){
				List<String> weekRecord = maintainRecordServiceInter.getWeekRecord(equipmentId, list.get(i).getId(), year, month);
				request.put("weekRecord"+i, weekRecord);
			}
			//保养人信息
			List<String> maintancePerson = maintainRecordServiceInter.getMaintenancePersonByWeek(equipmentId, list.get(0).getId(), year, month, 0);
			//确认人信息
			List<String> confirmPerson = maintainRecordServiceInter.getMaintenancePersonByWeek(equipmentId, list.get(0).getId(), year, month, 1);
			request.put("maintancePerson", maintancePerson);
			request.put("confirmPerson", confirmPerson);
			return "goWeekFormUI";
		}else if("month".equals(datetype)){
			for(int i=0;i<list.size();i++){
				List<String> monthRecord = maintainRecordServiceInter.getMonthRecord(equipmentId, list.get(i).getId(), year);
				request.put("monthRecord"+i, monthRecord);
			}
			//保养人信息
			List<String> maintancePerson = maintainRecordServiceInter.getMaintenancePersonByMonth(equipmentId, list.get(0).getId(), year, month, 0);
			//确认人信息
			List<String> confirmPerson = maintainRecordServiceInter.getMaintenancePersonByMonth(equipmentId, list.get(0).getId(), year, month, 1);
			request.put("maintancePerson", maintancePerson);
			request.put("confirmPerson", confirmPerson);
			return "goMonthFormUI";
			
		}else if("quarter".equals(datetype)){
			for(int i=0;i<list.size();i++){
				List<String> quarterRecord = maintainRecordServiceInter.getQuarterRecord(equipmentId, list.get(i).getId(), year);
				request.put("quarterRecord"+i, quarterRecord);
			}
			//保养人信息
			List<String> maintancePerson = maintainRecordServiceInter.getMaintenancePersonByQuarter(equipmentId, list.get(0).getId(), year, month, 0);
			//确认人信息
			List<String> confirmPerson = maintainRecordServiceInter.getMaintenancePersonByQuarter(equipmentId, list.get(0).getId(), year, month, 1);
			request.put("maintancePerson", maintancePerson);
			request.put("confirmPerson", confirmPerson);
			return "goQuarterFormUI";	
		}else if("halfyear".equals(datetype)){
			for(int i=0;i<list.size();i++){
				List<String> halfyearRecord = maintainRecordServiceInter.getHalfYearRecord(equipmentId, list.get(i).getId(), year);
				request.put("halfyearRecord"+i, halfyearRecord);
			}
			//保养人信息
			List<String> maintancePerson = maintainRecordServiceInter.getMaintenancePersonByHalfYear(equipmentId, list.get(0).getId(), year, month, 0);
			//确认人信息
			List<String> confirmPerson = maintainRecordServiceInter.getMaintenancePersonByHalfYear(equipmentId, list.get(0).getId(), year, month, 1);
			request.put("maintancePerson", maintancePerson);
			request.put("confirmPerson", confirmPerson);
			return "goHalfYearFormUI";
		}else if("year".equals(datetype)){
			for(int i=0;i<list.size();i++){
				List<String> yearRecord = maintainRecordServiceInter.getYearRecord(equipmentId, list.get(i).getId(), year);
				request.put("yearRecord"+i, yearRecord);
			}
			//保养人信息
			List<String> maintancePerson = maintainRecordServiceInter.getMaintenancePersonByYear(equipmentId, list.get(0).getId(), year, month, 0);
			//确认人信息
			List<String> confirmPerson = maintainRecordServiceInter.getMaintenancePersonByYear(equipmentId, list.get(0).getId(), year, month, 1);
			request.put("maintancePerson", maintancePerson);
			request.put("confirmPerson", confirmPerson);
			return "goYearFormUI";
		}
		return null;
	}
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		request=arg0;
	}

	public String getDatetype() {
		return datetype;
	}

	public void setDatetype(String datetype) {
		this.datetype = datetype;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getMachineVersion() {
		return machineVersion;
	}

	public void setMachineVersion(String machineVersion) {
		this.machineVersion = machineVersion;
	}

	public String getEquipmentEid() {
		return equipmentEid;
	}

	public void setEquipmentEid(String equipmentEid) {
		this.equipmentEid = equipmentEid;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
}
