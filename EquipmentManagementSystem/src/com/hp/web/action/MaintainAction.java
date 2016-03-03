package com.hp.web.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hp.domain.ConfirmInfo;
import com.hp.domain.Equipment;
import com.hp.domain.Line;
import com.hp.domain.MaintainItems;
import com.hp.domain.MaintainRecord;
import com.hp.domain.Users;
import com.hp.serviceInter.EquipmentServiceInter;
import com.hp.serviceInter.LineServiceInter;
import com.hp.serviceInter.MaintainItemsServiceInter;
import com.hp.serviceInter.MaintainRecordServiceInter;
import com.hp.utils.DateManage;
import com.hp.utils.Transform;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class MaintainAction extends ActionSupport implements RequestAware,SessionAware{

	private String lineName;//�߱���
	private String equipmentId;//�豸��
	private String datetype;//��������
	private int year=-1;//��ȡ����ȷ��ʱ�����¼��û�и�ֵʱΪ-1
	private int month=-1;//��ȡ����ȷ��ʱ���¼�¼
	private String equipmentName;
	private Map<String, Object> request;
	private Map<String, Object> session;
	//��¼������ĸ�������Ŀ����Ϣ
	private List<MaintainRecord> recodeList;
	//��¼����ȷ�ϵ���Ϣ
	private List<ConfirmInfo> confirmList;
	
	@Resource
	private EquipmentServiceInter equipmentServiceInter;
	@Resource
	private MaintainRecordServiceInter maintainRecordServiceInter;
	@Resource
	private MaintainItemsServiceInter maintainItemsServiceInter;
	@Resource
	private LineServiceInter lineServiceInter;
 	
	//��ת��ѡ���߱�UI
	public String goSelectUI(){
		request.put("type", "�豸��������");
		Users user=(Users) session.get("user");
		List<String> list = lineServiceInter.getLineNameByUser(user);
		request.put("LineName", list);
		return "goSelectUI";
	}
	//��ת��ѡ��������UI
	public String goMaintainUI(){
		request.put("type", "�豸��������");
		request.put("lineName", lineName);
		Users user=(Users) session.get("user");
		List<Equipment> list = equipmentServiceInter.getEquipmentListByUser(user);
		request.put("equipmentList", list);
		return "goMaintainUI";
	}
	//��ת���豸����������ĿUI
	public String goSecondMaintainUI(){
		
		//set equipment �� line��Ϣ �� �豸������Ϣ
		Equipment equipment = new Equipment();
		Line line = new Line();
		line.setName(lineName);
		equipment.setLine(line);
		equipment.setEid(equipmentId);
		//�����ݿ��ȡ��Ӧ�����equipment��Ϣ
		equipment=equipmentServiceInter.getResultByEid(equipment);
		
		//��������
		String[] type={"day","week","month","quarter","halfyear","year"};
		//�����ݿ�ȡ�������һ�α���ʱ��
		Timestamp[] lastTime = new Timestamp[6];
		//ת��ΪString�����Ϣ
		String[] lastTimeOfType =new String[6];
		for(int i=0;i<type.length;i++){
			if(equipment!=null){
				lastTime[i]=maintainRecordServiceInter.getLastTime(equipment.getEid(),type[i]);
				lastTimeOfType[i]=DateManage.date2String(lastTime[i]);
			}else{
				request.put("errInfo", "���豸���ڱ��߱�");
				//��ת��goMaintainUI���actionȥ
				return "goMaintainUIAction";
			}
		}
		String[] nextTimeOfType = DateManage.getNextTime(lastTime);
		//�豸����ʱ��
		int[] runTimeOfHour = DateManage.getRunTime(lastTime);//Сʱ
		int[] runTimeOfDay = new int[6];//����
		for(int i=0;i<6;i++){
			runTimeOfDay[i]=runTimeOfHour[i]/24;
		}

		if(equipment!=null && lastTimeOfType!=null){
			request.put("equipment", equipment);
			request.put("lastTimeOfType", lastTimeOfType);
			request.put("nextTimeOfType", nextTimeOfType);
			request.put("runTimeOfHour", runTimeOfHour);
			request.put("runTimeOfDay", runTimeOfDay);
			
		}
		request.put("type", "�豸��������");
		return "goSecondMaintainUI";
	}
	
	//��ת������������ĿUI
	public String goMaintainItems(){
		request.put("type", "�豸��������");
		//set equipment �� line��Ϣ �� �豸������Ϣ
		Equipment equipment = new Equipment();
		Line line = new Line();
		line.setName(lineName);
		equipment.setLine(line);
		equipment.setEid(equipmentId);
		//�����ݿ��ȡ��Ӧ�����equipment��Ϣ
		equipment=equipmentServiceInter.getResultByEid(equipment);
		List<MaintainItems> maintainItems = maintainItemsServiceInter.getMaintainItems(equipmentName, datetype);
		request.put("equipment", equipment);
		request.put("maintainItems", maintainItems);	
		return "goMaintainItems";
	}
	
	//���������ת������������ĿUI
	public String goMaintainOk(){
		Users user=(Users) session.get("user");
		//set equipment �� line��Ϣ �� �豸������Ϣ
		Equipment equipment = new Equipment();
		Line line = new Line();
		line.setName(lineName);
		equipment.setLine(line);
		equipment.setEid(equipmentId);
		//�����ݿ��ȡ��Ӧ�����equipment��Ϣ
		equipment=equipmentServiceInter.getResultByEid(equipment);
	
		//��ȡϵͳʱ��
		Date d = new Date();
		if(user!=null&&equipment!=null){
			//���ݼ�飬��������Ŀû��ͷ��ص�ǰҳ������ʾ������Ϣ
			for(MaintainRecord maintainRecord:recodeList){
				if(maintainRecord.getFirstResult()==null && (maintainRecord.getSecResult()==null || maintainRecord.getSecResult().length()==0)){
					request.put("lossInfo","������������Ϣ");
					//��ת��goMaintainItems Action
					return "goMaintainItemsAction";
				}
			}
			//��ȫ����Ŀ����д�ˣ���result���浽���ݿ�
			for(MaintainRecord maintainRecord:recodeList){
				maintainRecord.setUsersByUId(user);
				maintainRecord.setEquipment(equipment);
				maintainRecord.setMaintaintime(new Timestamp(d.getTime()));
				maintainRecordServiceInter.save(maintainRecord);
			}
		}
		//������� ��ת�� ����������ĿAction
		return "goSecondMaintainUIAction";
	}
	
	//���뵽������Ŀ��ѯUI
	public String goConfirmUI(){
		request.put("type", "����ȷ��");
		return "goConfirmUI";
	}
	//���뵽����ȷ����ĿUI
	public String queryConfirmObject(){
		request.put("type", "����ȷ��");
		if("nochoice".equals(datetype)){
			request.put("info", "��ѡ����������");
		}else if(year==-1){		
			request.put("info", "��ѡ�����");
		}else if(month==-1&&("day".equals(datetype)||"week".equals(datetype))){//day or week ����ʱ��������month��Ϣ
			request.put("info", "��ѡ���·�");
		}else{
			Users user=(Users) session.get("user");
			List<Object[]> list = maintainRecordServiceInter.getConfirmObject(year, month, datetype, user);
			List<String[]> maintainList = Transform.maintainItemsTransfom(list);
			request.put("datetype", datetype);
			request.put("maintainList", maintainList);
		}
		return "queryConfirmObject";
	}
	
	//����ȷ�Ϻ�Ķ�������תUI
	public String confirmOkUI(){
		Users user=(Users) session.get("user");
		MaintainRecord maintainRecord = new MaintainRecord();
		for(ConfirmInfo confirmInfo : confirmList){
			if("true".equals(confirmInfo.getFlag())){
				maintainRecord = (MaintainRecord) maintainRecordServiceInter.getById(confirmInfo.getRecordId(),MaintainRecord.class);
				maintainRecord.setUsersByEnId(user);
				maintainRecordServiceInter.save(maintainRecord);
			}
		}
		return "confirmOkUI";
	}
	
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		request=arg0;
	}
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		session=arg0;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}


	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getDatetype() {
		return datetype;
	}
	public void setDatetype(String datetype) {
		this.datetype = datetype;
	}
	public List<MaintainRecord> getRecodeList() {
		return recodeList;
	}
	public void setRecodeList(List<MaintainRecord> recodeList) {
		this.recodeList = recodeList;
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
	public List<ConfirmInfo> getConfirmList() {
		return confirmList;
	}
	public void setConfirmList(List<ConfirmInfo> confirmList) {
		this.confirmList = confirmList;
	}


}
