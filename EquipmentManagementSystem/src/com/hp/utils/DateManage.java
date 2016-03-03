package com.hp.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateManage {
	
	//�����ݿ�ȡ�������� 2015-11-11 18��16:37:097  ת��Ϊ 201511111816 ����ʽ
	public static String date2String(Timestamp date){
		if(date!=null){
			String time[]=date.toString().split(" ");
			String time1[]=time[0].split("-");
			String time2[]=time[1].split(":");
			String string="";
			for(int i=0;i<time1.length;i++){
				string += time1[i];
			}
			for(int i=0;i<time2.length-1;i++){
				string += time2[i];
			}
			return string;
		}else{
			return null;
		}
		
	} 
	
	//�����´α�������
	public static String[] getNextTime(Timestamp[] lastTime){
		Timestamp[] nextTime = new Timestamp[6];
		if(lastTime[0]!=null){
			nextTime[0] = new Timestamp(lastTime[0].getTime()+86400000L);//day
		}else{
			nextTime[0] = null;
		}
		if(lastTime[1]!=null){
			nextTime[1] = new Timestamp(lastTime[1].getTime()+86400000L*7);//week
		}else{
			nextTime[1]=null;
		}
		if(lastTime[2]!=null){
			nextTime[2] = new Timestamp(lastTime[2].getTime()+86400000L*30);//month
		}else{
			nextTime[2]=null;
		}
		if(lastTime[3]!=null){
			nextTime[3] = new Timestamp(lastTime[3].getTime()+86400000L*30*3);//quarter
		}else{
			nextTime[3]=null;
		}
		if(lastTime[4]!=null){
			nextTime[4] = new Timestamp(lastTime[4].getTime()+86400000L*30*6);//halfyear
		}else{
			nextTime[4] =null;
		}
		if(lastTime[5]!=null){
			nextTime[5] = new Timestamp(lastTime[5].getTime()+86400000L*365);//year
		}else{
			nextTime[5]=null;
		}
		String[] nextTimeOfType = new String[6];
		for(int i=0;i<6;i++){
			nextTimeOfType[i]=date2String(nextTime[i]);
		}
		return nextTimeOfType;
	}
	
	//���� ����ʱ�� û���ϴα�����¼ �� ����ʱ���0;
	public static int[] getRunTime(Timestamp[] lastTime){
		int[] runTimeOfHour = new int[6];
		Date currentTime = new Date();
		for(int i=0;i<6;i++){
			if(lastTime[i]!=null){
				runTimeOfHour[i] = (int)((currentTime.getTime()-lastTime[i].getTime())/3600000);	
			}else{
				runTimeOfHour[i] = 0;
			}
		}
		return runTimeOfHour;
	}

	//��ȡ ĳ�� ĳ�� 1�� ���ܼ� 
	/**
	 * @return 0 ������ �� 1:��һ��2���ܶ� �Դ�����
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getWeekIs(int year,int month){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String s = year+"-"+month+"-1";
		try {
			Date date = simpleDateFormat.parse(s);
			calendar.setTime(date);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return calendar.get(Calendar.DAY_OF_WEEK)-1;
	}
}
