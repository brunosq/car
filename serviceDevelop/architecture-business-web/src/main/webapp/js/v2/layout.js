function aboutLaout(){
	//获取元素html。documentElement 属性以一个元素对象返回一个文档的文档元素。HTML 文档返回对象为HTML元素。
	var htmlNode=document.documentElement;
	//getBoundingClientRect用于获得页面中某个元素的左，上，右和下分别相对浏览器视窗的位置
	var currentWidth=htmlNode.getBoundingClientRect().width;//获取当前元素宽度，无单位
	if(currentWidth>=3840){
		htmlNode.style.fontSize="100px";
	}else{
		htmlNode.style.fontSize=100*currentWidth/3840+"px";
	}
};
aboutLaout();
//addEventListener() 方法用于向指定元素添加事件句柄。
window.addEventListener("resize",aboutLaout,false);
