//公共的提示框
function msg(data,time){
	var myDialog = dialog({
		content:data
	});
	myDialog.show();
	setTimeout(function(){
		myDialog.close();
	},time)
}

function msg(data){
	var myDialog = dialog({
		content:data
	});
	myDialog.show();
	setTimeout(function(){
		myDialog.close();
	},2000)
}

//根据路径获取form表单
function getContentByurl(url){
	var content = "";
	$.ajax({
		url:url,
		type:"post",
		dataType:"html",
		data:{},
		async:false,//同步
		success:function(result){
			content = result;
		},
		error:function(){
			msg("系统错误，请联系客服",3000);
		}
	});
	return content;
}

  /*var Dialog = dialog({
		title:"修改",
		content:getContentByurl("<%=path%>/"),
		okValue:"确认",
		ok:function(){
			
		},
		cancelValue:"取消",
		cancel:function(){
		}
	})
	Dialog.showModal();*/
