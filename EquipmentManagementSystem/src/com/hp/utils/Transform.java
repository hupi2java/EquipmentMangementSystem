package com.hp.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Transform {

	//�����ݿ��ȡ�����ݽ�������ת��
	public static List<String[]> maintainItemsTransfom(List<Object[]> list){
		if(list!=null){
			List<String[]> maintainList = new ArrayList<String[]>();
			//��ȡ�������е�����
			//81-0|EQ13000123456-1|SPI-2|SE300 utal-3|G1B-4|2015-11-16 22:16:02.493-5|se300�ձ���1-6|��׼1-7|OK-8|A3070859-9
			for(Object[] obj:list){
				int j = 0;
				String[] objectString = new String[10];
				for(int i=0;i<12;i++){
					if(obj[i]!=null&&obj[i].toString()!=null){//�ų����ΪNULL��ֵ��firstResult/secResult����һ����Ϊ��
						if(i==8){
							//i=8ʱ �õ��� �������������ֵ ����ʡ��
						}else if(i==5){
							//i=5 ʱ ��ʱ��ת��ΪString
							objectString[j] = DateManage.date2String((Timestamp)obj[i]);
							j++;
						}else{
							objectString[j]=obj[i].toString();
							j++;
						}	
					}
				}
				maintainList.add(objectString);
			}
			return maintainList;
		}else{
			return null;
		}
	}

}
