package com.hp.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Transform {

	//从数据库获取的数据进行整理转换
	public static List<String[]> maintainItemsTransfom(List<Object[]> list){
		if(list!=null){
			List<String[]> maintainList = new ArrayList<String[]>();
			//获取型如下列的数据
			//81-0|EQ13000123456-1|SPI-2|SE300 utal-3|G1B-4|2015-11-16 22:16:02.493-5|se300日保养1-6|标准1-7|OK-8|A3070859-9
			for(Object[] obj:list){
				int j = 0;
				String[] objectString = new String[10];
				for(int i=0;i<12;i++){
					if(obj[i]!=null&&obj[i].toString()!=null){//排除结果为NULL的值，firstResult/secResult至少一个必为空
						if(i==8){
							//i=8时 得到的 保养结果的类型值 将其省略
						}else if(i==5){
							//i=5 时 将时间转换为String
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
