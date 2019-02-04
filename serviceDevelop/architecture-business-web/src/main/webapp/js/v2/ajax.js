var data001 = {     //  获取企业列表
	"province":""
};
var data002 = {     //  获取单个企业详情
	"companyId":''
}
// ajax请求
function getJSON(url,data,callback){
	$.ajax({
		// url : $.base+"/center/getCompanyList",
		url : "http://192.168.2.110:19910/bigScreen"+url,   //测试
		data : data,
		// async: false,
		type : "post",
		dataType : "json",
		success : callback,
		error : function(XMLHttpRequest,textStatus,errorThrown){
			alert(XMLHttpRequest.status);
			console.log('XMLHttpRequest.status:'+XMLHttpRequest.status);
			console.log('XMLHttpRequest.readyState:'+XMLHttpRequest.readyState);
			console.log('textStatus:'+textStatus);
		}
	});
}