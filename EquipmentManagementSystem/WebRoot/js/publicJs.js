//�������ǵ�һ��js ������

$(document).ready(function(){		
	
		//��ȡ�ձ���������ʱ��  �����жϣ�С��24Сʱ ��ʾ��ɫ��24-28Сʱ��ʾ��ɫ������28Сʱ��ʾ��ɫ
		 var timeOfDay = $("#runTimeOfDay").text();
		 if(timeOfDay<=24){
			$("#colorOfDay").css("background-color","green");
		}else if(timeOfDay>24 && timeOfDay<=28){
			$("#colorOfDay").css("background-color","yellow");
		}else if(timeOfDay>28){
			$("#colorOfDay").css("background-color","red");
		}
		 
		//��ȡ�ܱ���������ʱ��  �����жϣ�С��24*7Сʱ ��ʾ��ɫ��24*7-24*7+4Сʱ��ʾ��ɫ������24*7+4Сʱ��ʾ��ɫ
		 var timeOfWeek = $("#runTimeOfWeek").text();
		 if(timeOfWeek <= 24*7){
			 $("#colorOfWeek").css("background-color","green");
		 }else if(timeOfWeek>24*7 && timeOfWeek<=24*7+4){
			 $("#colorOfWeek").css("background-color","yellow");
		 }else if (timeOfWeek>24*7+4) {
			 $("#colorOfWeek").css("background-color","red");
		}
		 
		//��ȡ�±���������ʱ�� 
		 var timeOfMonth = $("#runTimeOfMonth").text();
		 if(timeOfMonth <= 24*30){
			 $("#colorOfMonth").css("background-color","green");
		 }else if(timeOfMonth>24*30 && timeOfMonth<=24*30+4){
			 $("#colorOfMonth").css("background-color","yellow");
		 }else if (timeOfMonth>24*30+4) {
			 $("#colorOfMonth").css("background-color","red");
		}
		 
		//��ȡ������������ʱ�� 
		 var timeOfQuarter = $("#runTimeOfQuarter").text();
		 if(timeOfQuarter <= 24*92){
			 $("#colorOfQuarter").css("background-color","green");
		 }else if(timeOfQuarter>24*92 && timeOfQuarter<=24*92+4){
			 $("#colorOfQuarter").css("background-color","yellow");
		 }else if (timeOfQuarter>24*92+4) {
			 $("#colorOfQuarter").css("background-color","red");
		}
		 
		 //��ȡ���걣��������ʱ�� 
		 var timeOfHalfYear = $("#runTimeOfHalfYear").text();
		 if(timeOfHalfYear <= 24*182){
			 $("#colorOfHalfYear").css("background-color","green");
		 }else if(timeOfHalfYear>24*182 && timeOfHalfYear<=24*182+4){
			 $("#colorOfHalfYear").css("background-color","yellow");
		 }else if (timeOfHalfYear>24*182+4) {
			 $("#colorOfHalfYear").css("background-color","red");
		}
		 
		 //��ȡ�걣��������ʱ��
		 var timeOfYear = $("#runTimeOfYear").text();
		 if(timeOfYear <= 24*365){
			 $("#colorOfYear").css("background-color","green");
		 }else if(timeOfYear>24*365 && timeOfYear<=24*365+4){
			 $("#colorOfYear").css("background-color","yellow");
		 }else if (timeOfYear>24*365+4) {
			 $("#colorOfYear").css("background-color","red");
		}
		 
		//ȫѡ
		 $("#allCheck").click(function(){
			 $("[type='checkbox']").attr("checked",'true'); 
		 });
		 //ȫ��ѡ
		 $("#moveCheck").click(function(){
			 $("[type='checkbox']").removeAttr("checked");
		 });
		 
		 //ѡȡday week ֮��ı������� �� ��ѡ���ʧЧ  ���Զ�ѡ�� ���� 
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
		 
		// ��ʾ������ �豸�����Ϣ ����ʼ�����ص�
		 $("#equipmentInfo").hide();
		 var i=0;
		 $("#showequipmentInfo").click(function(){
			 if(i==0){//�����ͨ��i��ֵ�ж���ʾ�������� �ٽ�����/�ԡ�ͬʱ�ı�i��ֵ
				 $("#equipmentInfo").show();
				 i=1;
			 }else if(i==1){
				 $("#equipmentInfo").hide();
				 i=0;
			 }
		 });
		 
		 
		 
});