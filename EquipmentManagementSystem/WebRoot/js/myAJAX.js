
$(document).ready(function(){
	
	//ͨ����������ȡ ������ 
	$("[name='bu']").change(function(){
		var bu = $("[name='bu']").val();
		
		$.ajax({
			url:"http://localhost:8080/EquipmentManagementSystem/getInfo_getDeparmentInfo.do?bu="+bu,
			type: "GET",
			success: function(data){

				$("#departmentInfo").empty();
				$("#departmentInfo").append("<option></option>");
				$("#departmentInfo").append(data);

			}
		});
	});
	 
	//ͨ������ID ��ȡ�ò����µ������߱�
	$("[name='departmentId']").change(function(){	
		getLine();
	});	
	
	getLine();
});

function getLine(){
	var departmentId = $("[name='departmentId']").val();
	$.ajax({
		url:"http://localhost:8080/EquipmentManagementSystem/getInfo_getLineInfoByDepartmentId.do?departmentId="+departmentId,
		type: "GET",
		success: function(data){
			$("#lineInfo").empty();
			$("#lineInfo").append("<option></option>");
			$("#lineInfo").append(data);
			setLine();
		}
	});
	
}

function setLine(){
	var lineId = document.getElementById("lineInfo").attributes["value"].value;
	$("#lineInfo").val(lineId);
}