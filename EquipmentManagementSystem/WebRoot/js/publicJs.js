//这里我们的一个js 函数库

$(document).ready(function(){		
	
		//获取日保养的运行时间  进行判断，小于24小时 显示绿色，24-28小时显示黄色，超过28小时显示红色
		 var timeOfDay = $("#runTimeOfDay").text();
		 if(timeOfDay<=24){
			$("#colorOfDay").css("background-color","green");
		}else if(timeOfDay>24 && timeOfDay<=28){
			$("#colorOfDay").css("background-color","yellow");
		}else if(timeOfDay>28){
			$("#colorOfDay").css("background-color","red");
		}
		 
		//获取周保养的运行时间  进行判断，小于24*7小时 显示绿色，24*7-24*7+4小时显示黄色，超过24*7+4小时显示红色
		 var timeOfWeek = $("#runTimeOfWeek").text();
		 if(timeOfWeek <= 24*7){
			 $("#colorOfWeek").css("background-color","green");
		 }else if(timeOfWeek>24*7 && timeOfWeek<=24*7+4){
			 $("#colorOfWeek").css("background-color","yellow");
		 }else if (timeOfWeek>24*7+4) {
			 $("#colorOfWeek").css("background-color","red");
		}
		 
		//获取月保养的运行时间 
		 var timeOfMonth = $("#runTimeOfMonth").text();
		 if(timeOfMonth <= 24*30){
			 $("#colorOfMonth").css("background-color","green");
		 }else if(timeOfMonth>24*30 && timeOfMonth<=24*30+4){
			 $("#colorOfMonth").css("background-color","yellow");
		 }else if (timeOfMonth>24*30+4) {
			 $("#colorOfMonth").css("background-color","red");
		}
		 
		//获取季保养的运行时间 
		 var timeOfQuarter = $("#runTimeOfQuarter").text();
		 if(timeOfQuarter <= 24*92){
			 $("#colorOfQuarter").css("background-color","green");
		 }else if(timeOfQuarter>24*92 && timeOfQuarter<=24*92+4){
			 $("#colorOfQuarter").css("background-color","yellow");
		 }else if (timeOfQuarter>24*92+4) {
			 $("#colorOfQuarter").css("background-color","red");
		}
		 
		 //获取半年保养的运行时间 
		 var timeOfHalfYear = $("#runTimeOfHalfYear").text();
		 if(timeOfHalfYear <= 24*182){
			 $("#colorOfHalfYear").css("background-color","green");
		 }else if(timeOfHalfYear>24*182 && timeOfHalfYear<=24*182+4){
			 $("#colorOfHalfYear").css("background-color","yellow");
		 }else if (timeOfHalfYear>24*182+4) {
			 $("#colorOfHalfYear").css("background-color","red");
		}
		 
		 //获取年保养的运行时间
		 var timeOfYear = $("#runTimeOfYear").text();
		 if(timeOfYear <= 24*365){
			 $("#colorOfYear").css("background-color","green");
		 }else if(timeOfYear>24*365 && timeOfYear<=24*365+4){
			 $("#colorOfYear").css("background-color","yellow");
		 }else if (timeOfYear>24*365+4) {
			 $("#colorOfYear").css("background-color","red");
		}
		 
		//全选
		 $("#allCheck").click(function(){
			 $("[type='checkbox']").attr("checked",'true'); 
		 });
		 //全不选
		 $("#moveCheck").click(function(){
			 $("[type='checkbox']").removeAttr("checked");
		 });
		 
		 //选取day week 之外的保养周期 后 月选择框失效  并自动选定 年月 
		 $("#datetype").change(function(){
			var type=$("#datetype").val();
			var mydate = new Date();
			var year = mydate.getFullYear();
			var month = mydate.getMonth();
			$("#year").val(year);
			if(type=='month' || type=='quarter' || type=='halfyear' || type=='year'){
				$("#month").attr("disabled",'disabled');
				$("#month").css("background-color","gray");
				
			}else{
				$("#month").removeAttr("disabled");
				$("#month").css("background-color","");
				$("#month").val(month+1);
			}
		 });
		 
		// 显示或隐藏 设备码的信息 ，初始是隐藏的
		 $("#equipmentInfo").hide();
		 var i=0;
		 $("#showequipmentInfo").click(function(){
			 if(i==0){//点击后通过i的值判断显示还是隐藏 再进行隐/显。同时改变i的值
				 $("#equipmentInfo").show();
				 i=1;
			 }else if(i==1){
				 $("#equipmentInfo").hide();
				 i=0;
			 }
		 });
		 
		 
		 
});