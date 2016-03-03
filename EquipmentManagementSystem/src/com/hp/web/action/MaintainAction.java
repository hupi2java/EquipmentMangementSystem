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

	private String lineName;//线别名
	private String equipmentId;//设备码
	private String datetype;//保养类型
	private int year=-1;//获取保养确认时的年记录，没有赋值时为-1
	private int month=-1;//获取保养确认时的月记录
	private String equipmentName;
	private Map<String, Object> request;
	private Map<String, Object> session;
	//记录保养后的各保养项目的信息
	private List<MaintainRecord> recodeList;
	//记录保养确认的信息
	private List<ConfirmInfo> confirmList;
	
	@Resource
	private EquipmentServiceInter equipmentServiceInter;
	@Resource
	private MaintainRecordServiceInter maintainRecordServiceInter;
	@Resource
	private MaintainItemsServiceInter maintainItemsServiceInter;
	@Resource
	private LineServiceInter lineServiceInter;
 	
	//跳转到选择线别UI
	public String goSelectUI(){
		request.put("type", "设备保养看板");
		Users user=(Users) session.get("user");
		List<String> list = lineServiceInter.getLineNameByUser(user);
		request.put("LineName", list);
		return "goSelectUI";
	}
	//跳转到选择保养机器UI
	public String goMaintainUI(){
		request.put("type", "设备保养看板");
		request.put("lineName", lineName);
		Users user=(Users) session.get("user");
		List<Equipment> list = equipmentServiceInter.getEquipmentListByUser(user);
		request.put("equipmentList", list);
		return "goMaintainUI";
	}
	//跳转到设备保养周期条目UI
	public String goSecondMaintainUI(){
		
		//set equipment 的 line信息 及 设备条码信息
		Equipment equipment = new Equipment();
		Line line = new Line();
		line.setName(lineName);
		equipment.setLine(line);
		equipment.setEid(equipmentId);
		//从数据库获取对应条码的equipment信息
		equipment=equipmentServiceInter.getResultByEid(equipment);
		
		//保养类型
		String[] type={"day","week","month","quarter","halfyear","year"};
		//从数据库取出的最后一次保养时间
		Timestamp[] lastTime = new Timestamp[6];
		//转化为String后的信息
		String[] lastTimeOfType =new String[6];
		for(int i=0;i<type.length;i++){
			if(equipment!=null){
				lastTime[i]=maintainRecordServiceInter.getLastTime(equipment.getEid(),type[i]);
				lastTimeOfType[i]=DateManage.date2String(lastTime[i]);
			}else{
				request.put("errInfo", "该设备不在本线别");
				//跳转到goMaintainUI这个action去
				return "goMaintainUIAction";
			}
		}
		String[] nextTimeOfType = DateManage.getNextTime(lastTime);
		//设备运行时间
		int[] runTimeOfHour = DateManage.getRunTime(lastTime);//小时
		int[] runTimeOfDay = new int[6];//天数
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
		request.put("type", "设备保养看板");
		return "goSecondMaintainUI";
	}
	
	//跳转到保养具体项目UI
	public String goMaintainItems(){
		request.put("type", "设备保养看板");
		//set equipment 的 line信息 及 设备条码信息
		Equipment equipment = new Equipment();
		Line line = new Line();
		line.setName(lineName);
		equipment.setLine(line);
		equipment.setEid(equipmentId);
		//从数据库获取对应条码的equipment信息
		equipment=equipmentServiceInter.getResultByEid(equipment);
		List<MaintainItems> maintainItems = maintainItemsServiceInter.getMaintainItems(equipmentName, datetype);
		request.put("equipment", equipment);
		request.put("maintainItems", maintainItems);	
		return "goMaintainItems";
	}
	
	//保养完后跳转到保养周期项目UI
	public String goMaintainOk(){
		Users user=(Users) session.get("user");
		//set equipment 的 line信息 及 设备条码信息
		Equipment equipment = new Equipment();
		Line line = new Line();
		line.setName(lineName);
		equipment.setLine(line);
		equipment.setEid(equipmentId);
		//从数据库获取对应条码的equipment信息
		equipment=equipmentServiceInter.getResultByEid(equipment);
	
		//获取系统时间
		Date d = new Date();
		if(user!=null&&equipment!=null){
			//数据检查，发现有项目没填就返回当前页，并显示出错信息
			for(MaintainRecord maintainRecord:recodeList){
				if(maintainRecord.getFirstResult()==null && (maintainRecord.getSecResult()==null || maintainRecord.getSecResult().length()==0)){
					request.put("lossInfo","请填完所有信息");
					//跳转到goMaintainItems Action
					return "goMaintainItemsAction";
				}
			}
			//若全部项目都填写了，将result保存到数据库
			for(MaintainRecord maintainRecord:recodeList){
				maintainRecord.setUsersByUId(user);
				maintainRecord.setEquipment(equipment);
				maintainRecord.setMaintaintime(new Timestamp(d.getTime()));
				maintainRecordServiceInter.save(maintainRecord);
			}
		}
		//保养完成 跳转到 保养周期项目Action
		return "goSecondMaintainUIAction";
	}
	
	//进入到保养项目查询UI
	public String goConfirmUI(){
		request.put("type", "保养确认");
		return "goConfirmUI";
	}
	//进入到保养确认项目UI
	public String queryConfirmObject(){
		request.put("type", "保养确认");
		if("nochoice".equals(datetype)){
			request.put("info", "请选择周期类型");
		}else if(year==-1){		
			request.put("info", "请选择年份");
		}else if(month==-1&&("day".equals(datetype)||"week".equals(datetype))){//day or week 周期时必须输入month信息
			request.put("info", "请选择月份");
		}else{
			Users user=(Users) session.get("user");
			List<Object[]> list = maintainRecordServiceInter.getConfirmObject(year, month, datetype, user);
			List<String[]> maintainList = Transform.maintainItemsTransfom(list);
			request.put("datetype", datetype);
			request.put("maintainList", maintainList);
		}
		return "queryConfirmObject";
	}
	
	//保养确认后的动作及跳转UI
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
