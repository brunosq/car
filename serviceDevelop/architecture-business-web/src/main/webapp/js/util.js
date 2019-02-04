var legendtextStyle={
		fontFamily: '微软雅黑',
       	fontSize: 12,
       	fontStyle: 'normal',
       	fontWeight: 'normal',
       	color:'#00b4ff'
};

var chinaMapLegend={
	orient: 'vertical',
    x:'left',
    y:'70%',
    selectedMode: 'single',
  	itemWidth: 20,
   	itemHeight: 20,
    data:[{name:'企业',
       textStyle: legendtextStyle,
       icon:'../images/icon_enterprise.png'
       },
       {name:'待报价货源',
    	textStyle:legendtextStyle,
    	icon:'../images/icon_goods_2.png'
    	},
        {name:'在途订单',
         textStyle:legendtextStyle,
         icon:'../images/icon_order.png'
        },
          {name:'闲置的车辆',
           textStyle: legendtextStyle,
           icon:'../images/icon_car_2.png'}
       ],
    selected:{
        '待报价货源' : false,
        '在途订单' : false,
        '运输中的路线' : false,
        '运输中的车辆' : false,
        '闲置的车辆' : false
    },
};

var chinaSerieItemStyle={
        normal:{
        	color :'transparent',
    		label:{show:false},
    		borderColor:'rgba(100,149,237,1)',
    	 	borderWidth:1,
    		areaStyle:{color: 'rgba(0,0,0,0)'}
        	},
        emphasis:{
    		label:{show:false},
        	areaStyle: {color: 'rgba(0,0,0,0)'},
        	borderWidth :2.5,
        	borderColor:'rgba(100,149,237,1)'
            }
    };


var childSerieItemStyle={
  	  normal:{
    		label:{show:false},
    		borderColor:'rgba(100,149,237,1)',
    	 	borderWidth:1,
    	 	areaStyle:{color: 'rgba(0,0,0,0)'}
    	},
      emphasis:{label:{show:false},
      	 	  areaStyle: {color: 'rgba(0,0,0,0)'}}
  };


function setNumber(dom, number){
	var n = String(number),len = n.length;
	//如果新的数字短于当前的，要移除多余的i
	if(dom.find("i").length > len){
		dom.find("i:gt(" + (len - 1) + ")").remove();
	}
	//移除千分位分隔符
	dom.find("b").remove();
	//开始填充每一位
	for(var i=0;i<len;++i){
		//位数不足要补
		if(dom.find("i").length < len){
			dom.append("<i></i>");
		}
		var obj = dom.find("i").eq(i);
		var y = -27* parseInt(n.charAt(i), 10);
		//加分隔符
		if(i < len - 1 && (len - i - 1) % 3 == 0)
			$("<b></b>").insertAfter(obj);
		//利用动画变换数字
		obj.animate({ backgroundPositionY:y+"px" }, 350);
	}
};



/*$.base*/
(function($){
    if (!$) {
        throw new Error("need jquery..");
        return;
    }
    
    $.base = (function(){
        var curWwwPath = window.document.location.href;
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);
        var localhostPath = curWwwPath.substring(0, pos);
        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
        return localhostPath + projectName;
    })();
    
})(jQuery);
