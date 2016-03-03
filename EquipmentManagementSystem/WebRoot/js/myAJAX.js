
$(document).ready(function(){
	
	//通过厂区名获取 部门名 
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
	 
	//通过部门ID 获取该部门下的所有线别
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