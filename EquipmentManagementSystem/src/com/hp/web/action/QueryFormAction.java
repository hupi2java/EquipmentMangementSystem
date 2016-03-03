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

	private String datetype;//��������
	private int year=-1;//��ȡ����ȷ��ʱ�����¼��û�и�ֵʱΪ-1
	private int month=-1;//��ȡ����ȷ��ʱ���¼�¼
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
	
	//��ת����ѯ����UI
	public String goQueryUI(){
		request.put("type", "�����ѯ����");
		return "goQueryUI";
	}
	
	//��ת����ѯ�����豸��UI
	public String goEquipmentListUI(){
		request.put("type", "�����ѯ����");
		List<Equipment> equipmentList=equipmentServiceInter.getAll();
		request.put("equipmentList", equipmentList);
		return  "goEquipmentListUI";
	}
	
	//��ת������UI
	public String goFormUI(){
		List<MaintainItems> list = maintainItemsServiceInter.getMaintainItems(equipmentId, datetype);
		request.put("maintainItems", list);
		if("day".equals(datetype)){
			//��ȡÿ����Ŀ�ı������ dayRecord0  �ǵ�һ�� ������Ŀ dayRecord1 �ǵ�2��������Ŀ �Դ�����
			for(int i=0;i<list.size();i++){
				List<String> dayRecord = maintainRecordServiceInter.getDayRecord(equipmentId, list.get(i).getId(), year, month);
				request.put("dayRecord"+i, dayRecord);
				request.put("dayCount", dayRecord.size());//���µ�����
			}
			if(list!=null&&list.size()>0){
				//��ȡ��������Ϣ
				List<String> maintancePerson = maintainRecordServiceInter.getMaintenancePersonByDay(equipmentId,list.get(0).getId(), year, month, 0);
				//��ȡ ȷ���� ��Ϣ
				List<String> confirmPerson = maintainRecordServiceInter.getMaintenancePersonByDay(equipmentId,list.get(0).getId(), year, month, 1);
				request.put("maintancePerson", maintancePerson);
				request.put("confirmPerson", confirmPerson);
			}
			return "goDayFormUI";
		}else if("week".equals(datetype)){
			//��ȡÿ����Ŀ�ı������ weekRecord0  �ǵ�һ�� ������Ŀ weekRecord1 �ǵ�2��������Ŀ �Դ�����
			for(int i=0;i<list.size();i++){
				List<String> weekRecord = maintainRecordServiceInter.getWeekRecord(equipmentId, list.get(i).getId(), year, month);
				request.put("weekRecord"+i, weekRecord);
			}
			//��������Ϣ
			List<String> maintancePerson = maintainRecordServiceInter.getMaintenancePersonByWeek(equipmentId, list.get(0).getId(), year, month, 0);
			//ȷ������Ϣ
			List<String> confirmPerson = maintainRecordServiceInter.getMaintenancePersonByWeek(equipmentId, list.get(0).getId(), year, month, 1);
			request.put("maintancePerson", maintancePerson);
			request.put("confirmPerson", confirmPerson);
			return "goWeekFormUI";
		}else if("month".equals(datetype)){
			for(int i=0;i<list.size();i++){
				List<String> monthRecord = maintainRecordServiceInter.getMonthRecord(equipmentId, list.get(i).getId(), year);
				request.put("monthRecord"+i, monthRecord);
			}
			//��������Ϣ
			List<String> maintancePerson = maintainRecordServiceInter.getMaintenancePersonByMonth(equipmentId, list.get(0).getId(), year, month, 0);
			//ȷ������Ϣ
			List<String> confirmPerson = maintainRecordServiceInter.getMaintenancePersonByMonth(equipmentId, list.get(0).getId(), year, month, 1);
			request.put("maintancePerson", maintancePerson);
			request.put("confirmPerson", confirmPerson);
			return "goMonthFormUI";
			
		}else if("quarter".equals(datetype)){
			for(int i=0;i<list.size();i++){
				List<String> quarterRecord = maintainRecordServiceInter.getQuarterRecord(equipmentId, list.get(i).getId(), year);
				request.put("quarterRecord"+i, quarterRecord);
			}
			//��������Ϣ
			List<String> maintancePerson = maintainRecordServiceInter.getMaintenancePersonByQuarter(equipmentId, list.get(0).getId(), year, month, 0);
			//ȷ������Ϣ
			List<String> confirmPerson = maintainRecordServiceInter.getMaintenancePersonByQuarter(equipmentId, list.get(0).getId(), year, month, 1);
			request.put("maintancePerson", maintancePerson);
			request.put("confirmPerson", confirmPerson);
			return "goQuarterFormUI";	
		}else if("halfyear".equals(datetype)){
			for(int i=0;i<list.size();i++){
				List<String> halfyearRecord = maintainRecordServiceInter.getHalfYearRecord(equipmentId, list.get(i).getId(), year);
				request.put("halfyearRecord"+i, halfyearRecord);
			}
			//��������Ϣ
			List<String> maintancePerson = maintainRecordServiceInter.getMaintenancePersonByHalfYear(equipmentId, list.get(0).getId(), year, month, 0);
			//ȷ������Ϣ
			List<String> confirmPerson = maintainRecordServiceInter.getMaintenancePersonByHalfYear(equipmentId, list.get(0).getId(), year, month, 1);
			request.put("maintancePerson", maintancePerson);
			request.put("confirmPerson", confirmPerson);
			return "goHalfYearFormUI";
		}else if("year".equals(datetype)){
			for(int i=0;i<list.size();i++){
				List<String> yearRecord = maintainRecordServiceInter.getYearRecord(equipmentId, list.get(i).getId(), year);
				request.put("yearRecord"+i, yearRecord);
			}
			//��������Ϣ
			List<String> maintancePerson = maintainRecordServiceInter.getMaintenancePersonByYear(equipmentId, list.get(0).getId(), year, month, 0);
			//ȷ������Ϣ
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
