 var chinaChart;
    
    var selectedProvince='';
    
    var companyPointData;
    var provinceCompnayData;
    
    var goodsPointData;
    var provinceGoodsData;
    
    var orderData;
    
    var carData;
    var provinceCarData;
    
    var nearMonthTrade;
    var goodsRate;
    var pricedRate;
    var orderRate;
    var goodsGrowthRate;
    var pricedGrowthRate;
    var orderGrowthRate;

	var isMapBankClick = true;
	
    
    //企业
    function getCompanyList(param){
    	$.ajax({
    		url : $.base+"/center/getCompanyList",
    		data : {
    			"province":param
    		},
    		async: false,
    		type : "post",
    		dataType : "json",
    		success : function(data) {
    			if(param !==''){//省份--企业
    				provinceCompnayData=new Array();
    				provinceCompnayData=data;
    			}else{//全国--企业
    				companyPointData=new Array();
    				companyPointData=data;
    			}
    		}
    	});
    }

    //待报价货源
    function getGoodsList(param){
    	$.ajax({
    		url : $.base+"/center/getGoodsList",
    		data : {
    			"province":param
    		},
    		async: false,
    		type : "post",
    		dataType : "json",
    		success : function(data) {
    			if(param!==''){//省份--待报价货源
    				provinceGoodsData=new Array();
    				provinceGoodsData=data;
    			}else{//全国--待报价货源
    				goodsPointData=new Array();
    				goodsPointData=data;
    			}
    		}
    	});
    }
    
   //订单
    function getOrderList(param){
    	$.ajax({
    		url : $.base+"/center/getOrderList",
    		async: false,
    		type : "post",
    		dataType : "json",
    		success : function(data) {
    			orderData=new Array();
    			orderData=data;
    		}
    	});
    }
   
    //闲置的车辆list
    function getUnusedCarList(param){
    	$.ajax({
    		url : $.base+"/center/getUnusedCarList",
    		async: false,
    		type : "post",
    		data : {
    			"province":param
    		},
    		dataType : "json",
    		success : function(data) {
    			if(param!==''){//省份--待报价货源
    				provinceCarData=new Array();
    				provinceCarData=data;
    			}else{//全国--待报价货源
    				carData=new Array();
        			carData=data;
    			}
    			
    		}
    	});
    }
    
    
    //全国交易额
    function getTotalTradeMoney(){
    	$.ajax({
			url : $.base+"/center/getTotalTradeMoney",
			data:{"agent_code":"65"},
			async: true,
			type : "post",
			dataType : "json",
			success : function(data) {
				//$('#arrowsDiv').html('');
				parent.$("#arrowsDiv", parent.document).html('');
				if($('#totalTradeMoneyHide').val()!=data.totalTradeMoney){
					
					for (var i=0;i<data.newOrderLocas.length;i++){
					//for (var i=0;i<4;i++){
						//var loc=chinaChart.chart.map.getPosByGeo('china',data.newOrderLocas[i]);//地图坐标位置转化为页面位置
						var loc = [555.7563870913286, 565.7869588425066];
						var width=loc[0]+518+i*80;
						var height=loc[1]-250;
						
						var html="<img class='arrows' style='margin-top:"+height+"px;margin-left:"+width+"px' src='../images/arrow_1.png'/>";
						 
						parent.$("#arrowsDiv", parent.document).append(html);
					}
		    		
					parent.$(".arrows", parent.document).each(function(){
	    				 $(this).slideUp(2000,function(){
	    			 		 $(this).css('display','none');
	    			 	});
		    		 });
	    			 
				} 
				
				
				$('#totalTradeMoneyHide').val(data.totalTradeMoney)
				
				setTotalNumber($("#totalTradeMoney"), data.totalTradeMoney);
				setProvinceTotalNumber($("#provincePrice"), data.provincePrice);
				
			}
		});  	
    }
    
    //最新六个月数据交易额
    function getNearMonthTrade(){
    	$.ajax({
			url : $.base+"/center/getNearMonthTrade",/* 650是江西省编码的前几位，如果不传，则表示找的全国的交易额 */
			data:{"agent_code":"65"},
			async: false,
			type : "post",
			dataType : "json",
			success : function(data) {
				nearMonthTrade=new Array();
				//var testData = [{"trade":260058330,"month":"2017-06"},{"trade":406992232,"month":"2017-05"},{"trade":209670265,"month":"2017-04"},{"trade":129880847,"month":"2017-03"},{"trade":83078393,"month":"2017-02"},{"trade":75088582,"month":"2017-01"}];
				nearMonthTrade=data;
			}
		});  	
    }
    
    //货源发布率，货源报价率，货源成单率，货源发布同比增长，货源报价同比增长，货源成单同比增长
    function getGoodsRate(){
    	$.ajax({
    		url : $.base+"/center/getGoodsRate",
    		data:{"agent_code":"65"},
    		async: false,
    		type : "post",
    		dataType : "json",
    		success : function(data) {
    		    goodsRate=data.goodsRate;
    		    pricedRate=data.pricedRate;
    		    orderRate=data.orderRate;
    		    goodsGrowthRate=data.goodsGrowthRate;
    		    pricedGrowthRate=data.pricedGrowthRate;
    		    orderGrowthRate=data.orderGrowthRate;
    		}
    	});
    }
    
   //定时刷新统计数据
    function runTimeTask(){
	    window.setInterval(function(){
	    	getTotalTradeMoney();
		}, 1000*60*2.5);
    }
    
  	//点击省份事件,查詢省份待报价货源
    function showProvinceGoods(param){
    	$.ajax({
    		url : $.base+"/center/getProvinceGoods",
    		data : {
    			"province":param
    		},
    		async: true,
    		type : "post",
    		dataType : "json",
    		success : function(data) {
    			 if(param!==''){//省份--待报价货源
    				$('.rightGoods').html('');
    				var htmlDiv='';
    				$.each(data, function (index, item) {
    					htmlDiv +='<div class="provinceGoods" onmouseenter="mouseenterGoods(this)" onmouseleave="mouseleaveGoods(this)" onclick="showProGoodDetail('+item.goodsId+')">';
    					htmlDiv +='<img src="../images/icon_01.png" class="icon_01">';
    					htmlDiv +='<table style="margin-left: 7%;margin-top: 1%;">';
    					htmlDiv +='<tr>';
    					if(item.startCounty==null){
    						htmlDiv +='<td class="addr" style="width:10%">'+item.startCity+'</td>';
    					}else{
    						htmlDiv +='<td class="addr" style="width:10%">'+item.startCounty+"（"+item.startCity+"）"+'</td>';
    					}
    					htmlDiv +='<td style="width:15%"><img style="height: 15px;margin-right: 5px" src="../images/icon_distance.png"/>'+item.distance+'公里</td>';
    					htmlDiv +='<td style="width:15%"><img style="height: 15px;margin-right: 5px" src="../images/icon_calendar.png"/>'+item.startTime+'</td>';
    					htmlDiv +='</tr>';
    					htmlDiv +='<tr>';
    					if(item.destCounty==null){
    						htmlDiv +='<td class="addr" >'+item.destCity+'</td>';
    					}else{
							var newChar = item.destCounty+"（"+item.destCity+"）";
							if(newChar.length >= 10)
							{
								htmlDiv +='<td class="addr" >'+item.destCity+'</td>';
							}else{
								htmlDiv +='<td class="addr" >'+newChar+'</td>';
							}
    					}
    					if(1==item.isWeightGoods){
    						htmlDiv += '<td colspan="2">车型：'+item.carType+'<span class="separate">|</span>重量：'+item.weight+'吨<span class="separate">|</span>车长：'+item.carLength+'米</td>';
    					}else{
    						htmlDiv += '<td colspan="2">车型：'+item.carType+'<span class="separate">|</span>体积：'+item.volume+'方<span class="separate">|</span>车长：'+item.carLength+'米</td>';
    					}
    					htmlDiv +='</tr>';
    					htmlDiv +='</table>';
    					htmlDiv +='</div>';
    				});
					 $('.rightGoods').append(htmlDiv);
    			} 
    		}
    	});
    }


    
    //渲染全国地图
    function showChinaMap(){
    		 chinaChart = echarts.init(document.getElementById('map'));
    		 chinaOption = {
		        backgroundColor: 'rgba(0,0,0,0)',
			    //图列
			    legend: chinaMapLegend,
			    tooltip : {
	                trigger: 'item',
	                formatter: '{b}'
	            },
			    series:[
					/****************************************************企业*******************************************************/
			            {
			            name: '企业',
			            type: 'map',
			            mapType: 'china',
			            mapLocation: {
			                x: 'center',
			                y: 'center',
			                width: '100%'
			            },
			            selectedMode: 'single',
			            roam: false,
			            itemStyle: chinaSerieItemStyle,
			            //数据源
			            data:selectCiyts,
			            markPoint : {
			            	symbol:'circle',
			                itemStyle:{
			                	normal: {
			                	color:'#a3db78',
		                        label: {
		                            show: false
		                        }
		                    },
		                    emphasis: {
		                        borderColor: '#1e90ff',
		                        borderWidth: 3,
		                        label: {
		                            show: false
		                        }
		                    }
			                },
			                //symbol: 'image://../images/icon_enterprise_1.png',
			                symbolSize: function (val) {
			                	if(1000<val){
			                		return 15;
			                	}
		                	 	if(500<val && val<=1000){
			                		return 13;
			                	}
			                	 if(300<val && val<=500){
			                		return 11;
			                	}
			                	if(100<val && val<=300){
			                		return 10;
			                	}
			                	if(50<val && val<=100){
			                		return 8;
			                	}
			                	if(2<val && val<=50){
			                		return 7;
			                	}
			                	if(0<val && val<=20){
			                		return 5;
			                	}
			                	return 5;
			                },
			                data:companyPointData
			            },
			            geoCoord:geoCityMap
			        },
			        /**************************************************待报价货源*********************************************************/
			        {
			            name: '待报价货源',
			            type: 'map',
			            mapType: 'china',
			            mapLocation: {
			                x: 'center',
			                y: 'center',
			                width: '100%'
			            },
			            roam: false,
			            selectedMode: 'single',
			            itemStyle:chinaSerieItemStyle,
			            //数据源
			            data:selectCiyts,
			            markPoint : {
			            	symbol:'circle',
			                //symbolSize: 6,
			                //symbol: 'image://../images/goods.svg',
			                itemStyle:{
			                	normal: {
				                	color:'#38e4c2',
			                        label: {
			                            show: false
			                        }
		                    	},
			                    emphasis: {
			                        borderColor: '#38e4c2',
			                        borderWidth: 3,
			                        label: {
			                            show: false
			                        }
			                    }
			                },
			                symbolSize: function(val){
			                	if(200<val){
			                		return 15;
			                	}
		                	 	if(150<val && val<=200){
			                		return 13;
			                	}
			                	 if(100<val && val<=150){
			                		return 11;
			                	}
			                	if(30<val && val<=50){
			                		return 10;
			                	}
			                	if(20<val && val<=30){
			                		return 8;
			                	}
			                	if(0<val && val<=20){
			                		return 5;
			                	}
			                	return 2;
		                	},
		                	effect:{
		                		show: false,
		                	},
			                data:goodsPointData
			            }
			        },
			        /**************************************************在途订单*********************************************************/
			        {
			            name: '在途订单',
			            type: 'map',
			            mapType: 'china',
			            mapLocation: {
			                x: 'center',
			                y: 'center',
			                width: '100%'
			            },
			            roam: false,
			            selectedMode: 'single',
			            itemStyle:chinaSerieItemStyle,
			            //数据源
			            data:selectCiyts,
			            markLine : {
			            	symbol: ['emptyCircle','emptyCircle'],
			                symbolSize : 3,
			                effect :  {
			            	    show: true,
			            		scaleSize: 1,
			            	    smoothness: 0.7,
			            	    period: 30,
			            	    color: '#fff',
			            	    shadowBlur : 1
			            	},
			                itemStyle : {
			                		normal: {
					    	            borderWidth:0.7,
					    	            borderColor:'#59bbd6',
					    	            lineStyle: {
					    	                type: 'solid'
					    	            }
			                		}
			           			},
			                smooth:true,
			                data : orderData  /* [
		                        	[{name:"北京北京"},{name:"江苏南京"}],
		                        	[{name:"江苏南京"},{name:"北京北京"}]
		                        	] */
			            }
			        },
			        /**************************************************闲置的车辆*********************************************************/
			        {
			            name: '闲置的车辆',
			            type: 'map',
			            mapType: 'china',
			            mapLocation: {
			                x: 'center',
			                y: 'center',
			                width: '100%'
			            },
			            roam: false,
			            selectedMode: 'single',
			            itemStyle:chinaSerieItemStyle,
			            //数据源
			            data:selectCiyts,
			            markPoint : {
			            	symbol:'circle',
			                //symbolSize: 6,
			                //symbol: 'image://../images/car03.png',
			            	symbolSize: function(val){
			                	if(6000<val){
			                		return 15;
			                	}
		                	 	if(5000<val && val<=6000){
			                		return 15;
			                	}
			                	 if(4000<val && val<=5000){
			                		return 11;
			                	}
			                	if(3000<val && val<=4000){
			                		return 10;
			                	}
			                	if(1000<val && val<=2000){
			                		return 7;
			                	}
			                	if(500<val && val<=1000){
			                		return 7;
			                	}
			                	if(300<val && val<=500){
			                		return 5;
			                	}
			                	if(0<val && val<=300){
			                		return 4;
			                	}
			                	return 4;
		                	},
			                itemStyle:{
			                	normal: {
				                	color:'#dd773b',
			                        label: {
			                            show: false
			                        }
		                    	},
			                    emphasis: {
			                        borderColor: '#38e4c2',
			                        borderWidth: 3,
			                        label: {
			                            show: false
			                        }
			                    }
			                },
			                data: carData
			            }
			        }

			    ]
		};

		chinaChart.setOption(chinaOption, true);
		
		var isSelectedMap=false;
		 //单击省份事件 
		chinaChart.on(echarts.config.EVENT.MAP_SELECTED, function (param){

			isMapBankClick = false;
			 isSelectedMap=true;

			 comListClose();

			 showProvinceGoods(param.target);
			 var myurl = "bottom-right.html?param="+param.target;
			 parent.$("#rightIframe", parent.document).attr("src",encodeURI(myurl));

		});
		
		/* 单击marketPoint事件 */
		chinaChart.on(echarts.config.EVENT.CLICK, function (param){
			isMapBankClick = false;

			if(!isSelectedMap){
				if(param.seriesIndex=='0'){
					getCompanyListByCity(param);
				}else if(param.seriesIndex=='1'){
					getGoodsListByCity(param);
				}else if(param.seriesIndex=='2'){
					getOrderListByCity(param);
				}else if(param.seriesIndex=='3'){
					getUnusedCarListByCity(param);
				}
			}
			isSelectedMap=false;
		});

		/*点击legend事件*/
		chinaChart.on(echarts.config.EVENT.LEGEND_SELECTED, function (param){
			console.info(param);
			comListClose();
		});
		
     }
  	
 	//货源发布率，货源报价率，货源成单率，货源发布同比增长，货源报价同比增长，货源成单同比增长
    function showRate(){
	    	var rateChart = echarts.init(document.getElementById('left_bottom'));
	    	var lable = {
    		    normal : {
    		    	color: '#008ee5',
    		        label : {
                        formatter : function (params){
    		                return params.value + '%';
    		            },
    		            textStyle:{
    		            	fontSize:16,
    		            	fontWeight:'bold'
    		            },
    		            position : 'center'
    		        },
    		        labelLine : {
    		            show : false
    		        }
    		    }
    		};
	    	
	    	var otherLabel = {
	    		    normal : {
	    		        color: '#02303f',
	    		        label : {
	    		            show : false
	    		        },
	    		        labelLine : {
	    		            show : false
	    		        },
	    		    }
	    		};
    		
    		var radius = [37, 48];
    		var innerradius = [28, 33];
    		
    		rateOption = {
    			legend:{
    				show: false,
    				selectedMode: false,
    				itemGap: 60,
    				itemWidth: 0,
    				itemHeight: 0,
    				y:'45%',
    				x:'10%',
    				data:[
    				      {name:'货源发布率环比增长',textStyle:legendtextStyle},
    				      {name:'货源报价率环比增长',textStyle:legendtextStyle},
    				      {name:'货源成单率环比增长',textStyle:legendtextStyle},
    				      {name:''},
    				      {name:''},
    				      {name:''},
    				      {name:'货源发布率同比增长',textStyle:legendtextStyle},
    				      {name:'货源报价率同比增长',textStyle:legendtextStyle},
    				      {name:'货源成单率同比增长',textStyle:legendtextStyle}
    				      ]
    			},
    		    series : [
    		        {
    		            type : 'pie',
    		            center : ['20%', '30%'],
    		            clockWise:false,
    		            radius : radius,
    		            data : [
    		                {name:'other', value:0, itemStyle : otherLabel},
    		                {name:'货源发布率环比增长', value:goodsRate.replace(",",""), itemStyle :lable}
    		            ]
    		        },
    		        {
    		            type : 'pie',
    		            center : ['20%', '30%'],
    		            radius : innerradius,
    		            clockWise:true,
    		            data : [
    		                {name:'other', value:0,itemStyle: otherLabel},
    		                {name:'货源发布率环比增长', value:goodsRate.replace(",",""), itemStyle:lable}
    		            ]
    		        },
    		        {
			            type : 'pie',
			            center : ['50%', '30%'],
			            clockWise:false,
			            radius : radius,
			            data : [
			                {name:'other', value:0, itemStyle : otherLabel},
			                {name:'货源报价率环比增长', value:pricedRate.replace(",",""),itemStyle : lable}
			            ]
			        },
			        {
			            type : 'pie',
			            center : ['50%', '30%'],
			            clockWise:true,
			            radius : innerradius,
			            data : [
			                {name:'other', value:0, itemStyle : otherLabel},
			                {name:'货源报价率环比增长', value:pricedRate.replace(",",""),itemStyle : lable}
			            ]
			        },
			         {
			            type : 'pie',
			            center : ['80%', '30%'],
			            clockWise:false,
			            radius : radius,
			            data : [
			                {name:'other', value:0, itemStyle : otherLabel},
			                {name:'货源成单率环比增长', value:orderRate.replace(",",""),itemStyle : lable}
			            ]
			        },
			        {
			            type : 'pie',
			            center : ['80%', '30%'],
			            clockWise: true,
			            radius : innerradius,
			            data : [
			                {name:'other', value:0, itemStyle : otherLabel},
			                {name:'货源成单率环比增长', value:orderRate.replace(",",""),itemStyle : lable}
			            ]
			        },
			        {
			            type : 'pie',
			            center : ['20%', '70%'],
			            clockWise:false,
			            radius : radius,
			            data : [
			                {name:'other', value:0, itemStyle : otherLabel},
			                {name:'货源发布率同比增长', value: goodsGrowthRate.replace(",",""),itemStyle : lable}
			            ]
			        },
			        {
			            type : 'pie',
			            center : ['20%', '70%'],
			            clockWise: true,
			            radius : innerradius,
			            data : [
			                {name:'other', value:0, itemStyle : otherLabel},
			                {name:'货源发布率同比增长', value:goodsGrowthRate.replace(",",""),itemStyle : lable}
			            ]
			        },
			        {
			            type : 'pie',
			            center : ['50%', '70%'],
			            clockWise:false,
			            radius : radius,
			            data : [
			                {name:'other', value:0, itemStyle : otherLabel},
			                {name:'货源报价率同比增长', value:pricedGrowthRate.replace(",",""),itemStyle : lable}
			            ]
			        },
			        {
			            type : 'pie',
			            center : ['50%', '70%'],
			            clockWise: true,
			            radius : innerradius,
			            data : [
			                {name:'other', value:0, itemStyle : otherLabel},
			                {name:'货源报价率同比增长', value:pricedGrowthRate.replace(",",""),itemStyle : lable}
			            ]
			        },
			        {
			            type : 'pie',
			            center : ['80%', '70%'],
			            clockWise:false,
			            radius : radius,
			            data : [
			                {name:'other', value:0, itemStyle : otherLabel},
			                {name:'货源成单率同比增长', value:orderGrowthRate.replace(",",""),itemStyle : lable}
			            ]
			        },
			        {
			            type : 'pie',
			            center : ['80%', '70%'],
			            clockWise: true,
			            radius : innerradius,
			            data : [
			                {name:'other', value:0, itemStyle : otherLabel},
			                {name:'货源成单率同比增长', value:orderGrowthRate.replace(",",""),itemStyle : lable}
			            ]
			        }
			        
    		    ]
    		};
    		 rateChart.setOption(rateOption, true);
 	}
 	
 	//交易额趋势图
 	function getTradeChart(){
 		var tradeChart = echarts.init(document.getElementById('tradeChart'));
 		
 		userSticsOption = {
 				  tooltip: {
 				       formatter: "{c}"
 				   },
 			   title : {
 			        text: '交易额趋势图',
 			        x: 'center',
 			        y: 'top',
 			        textStyle:{
 			        	color:'#008ee5',
 			        	fontSize:15
 			        }
 			    },
 			    calculable : false,
 			   	grid:{
 			   		width:'100%',
 			   		height:'70%',
 			   		borderWidth: 0,
 			   		x:'1%',
 			   	    y:'20%'
 			   	},
 			    xAxis : [ {
 			            type : 'category',
 			            data : [nearMonthTrade[5].month, nearMonthTrade[4].month, nearMonthTrade[3].month, nearMonthTrade[2].month, nearMonthTrade[1].month, nearMonthTrade[0].month],
 			            axisLabel: {
 			        	  show: true,
 			        	  margin:0,
 			        	  textStyle:{
 			        		color:'#4b5861',
 			        		fontFamily:'微软雅黑',
 			        		fontSize:12
 			        	  }
 			        	},
 			        	axisLine: {
 			        		show: false
 			        	},
 			        	splitLine:{
 			        		show: false
 			        	}
 			        }],
 			     yAxis : [{
 			            type : 'value',
 			        	axisLabel: {
 	 			        	  show: false
 	 			        	},
 			        	axisLine: {
 			        		show: false
 			        	},
 			        	splitLine:{
 			        		show: false
 			        	}
 			        }],
 			    series : [
 			        {
 			            type:'bar',
 			            barWidth:20,
 			            barGap:20,
 			            itemStyle:{
 			            	normal:{
 			            		color:'#008ee5'
 			            	}   
 			           },
 			            data:[nearMonthTrade[5].trade, nearMonthTrade[4].trade, nearMonthTrade[3].trade, nearMonthTrade[2].trade, nearMonthTrade[1].trade, nearMonthTrade[0].trade]
 			        }
 			    ]
 			};
 		tradeChart.setOption(userSticsOption,true);
 	}
 	
	function setTotalNumber(dom, number){
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
			var y = -60* parseInt(n.charAt(i), 10);
			//加分隔符
			if(i < len - 1 && (len - i - 1) % 3 == 0)
				$("<b></b>").insertAfter(obj);
			//利用动画变换数字
			obj.animate({ backgroundPositionY:y+"px" }, 350);
		}
	};
	function setProvinceTotalNumber(dom, number){
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
			var y = -45* parseInt(n.charAt(i), 10);
			//加分隔符
			if(i < len - 1 && (len - i - 1) % 3 == 0)
				$("<b></b>").insertAfter(obj);
			//利用动画变换数字
			obj.animate({ backgroundPositionY:y+"px" }, 350);
		}
	};
	
	function mouseenter(thisP){
		$(thisP).css('background-color','#3071b7');
	}
	
	function mouseleave(thisP){
		$(thisP).css('background-color','#0e4a8a');
	}
	
	function mouseenterGoods(thisP){
		$(thisP).css('background-image','url(../images/list_pre.png)');
	}
	
	function mouseleaveGoods(thisP){
		$(thisP).css('background-image','url(../images/icon_frame.png)');
	}
 	
	function showCompanyDetail(){
		$('#companyDetail').css('display','block');
	}
	
	/*根据city查询，企业List*/
	function getCompanyListByCity(param){
		comListClose();
		$.ajax({
			url : $.base+"/center/getCompanyList",
			data : {
				"city":param.name
			},
			async: true,
			type : "post",
			dataType : "json",
			success : function(data) {
				var html='';
				$.each(data, function (index, item) {
					html+="<p onmouseenter='mouseenter(this)' onmouseleave='mouseleave(this)' onclick='showCompanyDetail("+item.id+")'>"+item.companyName+"</p>";
				});
				
				$('#companyList').append(html);
				
				$('#companyList').css('display','block');
				
				$('#companyListClose').css('display','block');
			}
		});
	}
	
	/*根据city查询，货源List*/
	function getGoodsListByCity(param){
		
		comListClose();
		
		$.ajax({
			url : $.base+"/center/getGoodsList",
			data : {
				"city":param.name
			},
			async: true,
			type : "post",
			dataType : "json",
			success : function(data) {
				var html='';
				$.each(data, function (index, item) {
					var temp;
					if(item.startCounty==null || item.destCounty==null){
						if(item.startCounty==null && item.destCounty==null){
							temp=item.startCity+"--"+item.destCity+"<span class='distanceStyle'>"+item.distance+"公里"+"</span>";
						}else{
							if(item.startCounty==null){
								temp=item.startCity+"--"+item.destCounty+"("+item.destCity+")"+"<span class='distanceStyle'>"+item.distance+"公里"+"</span>";
							}
							if(item.destCounty==null){
								temp=item.startCounty+"("+item.startCity+")--"+item.destCity+"<span class='distanceStyle'>"+item.distance+"公里"+"</span>";
							}
						}
					}else{
						temp=item.startCounty+"("+item.startCity+")"+"--"+item.destCounty+"("+item.destCity+")"+"<span class='distanceStyle'>"+item.distance+"公里"+"</span>";
					}
					
					html+="<p onmouseenter='mouseenter(this)' onmouseleave='mouseleave(this)' onclick='showGoodsDetail("+item.goodsId+")'>"+temp+"</p>";
				});
				
				$('#companyList').append(html);
				
				$('#companyList').css('display','block');
				
				$('#companyListClose').css('display','block');
			}
		});
	}
	
	
	//根据订单出发地、目的地查询订单list
	function getOrderListByCity(param){
		
		comListClose();
		
		var name=param.name.split("\>");
		var startCity=name[0];
		var destCity=name[1];
		$.ajax({
			url : $.base+"/center/getOrderListByCity",
			data : {
				"startCity":startCity,
				"destCity":destCity
			},
			async: true,
			type : "post",
			dataType : "json",
			success : function(data) {
				var html='';
				$.each(data, function (index, item) {
					var temp;
					if(item.startCounty==null || item.destCounty==null){
						if(item.startCounty==null && item.destCounty==null){
							temp=item.startCity+"--"+item.destCity;
						}else{
							if(item.startCounty==null){
								temp=item.startCity+"--"+item.destCounty+"("+item.destCity+")";
							}
							if(item.destCounty==null){
								temp=item.startCounty+"("+item.startCity+")--"+item.destCity;
							}
						}
					}else{
						temp=item.startCounty+"("+item.startCity+")"+"--"+item.destCounty+"("+item.destCity+")";
					}
					html+="<p onmouseenter='mouseenter(this)' onmouseleave='mouseleave(this)' onclick='showOrderDetail("+item.orderId+")'>"+temp+"</p>";
				});
				
				$('#companyList').append(html);
				
				$('#companyList').css('display','block');
				
				$('#companyListClose').css('display','block');
			}
		});
		
	}
	
	/*根据city查询，闲置车辆List*/
	function getUnusedCarListByCity(param){
		
		comListClose();
		
		$.ajax({
			url : $.base+"/center/getUnusedCarList",
			data : {
				"city":param.name
			},
			async: true,
			type : "post",
			dataType : "json",
			success : function(data) {
				var html='';
				$.each(data, function (index, item) {
					var num = item.carNum.substring(0,item.carNum.length-4)+"****";
					html+="<p onmouseenter='mouseenter(this)' onmouseleave='mouseleave(this)' onclick='showCarDetail("+item.id+")'>"+item.driverName+"---"+num+"</p>";
				});

//				$.each(data, function (index, item) {
//					html+="<p onmouseenter='mouseenter(this)' onmouseleave='mouseleave(this)' onclick='showCarDetail("+item.id+")'>"+item.driverName+"---"+item.carNum+"</p>";
//				});
				
				$('#companyList').append(html);
				
				$('#companyList').css('display','block');
				
				$('#companyListClose').css('display','block');
			}
		});
	}
	
	/*企业详情*/
	function showCompanyDetail(companyId){
		
		$('#companyDetail').html('');
		
		$.ajax({
			url : $.base+"/center/getCompanyDetail",
			data : {
				"companyId":companyId
			},
			async: true,
			type : "post",
			dataType : "json",
			success : function(data) {
				//判断是否是江西的区域注册码
				var agentCode = data.agentCode.toString();
				var str = agentCode.substr(0,3);
				if(str != '65'){
					return;
				}
				
				var companyMobile = data.companyMobile.substring(0,data.companyMobile.length-4)+"****";

				var html="<p style='font-size: 15px'>企业详情</p>";
					html+="<p>企业："+data.companyName+"</p>";
					html+="<p>联系电话："+companyMobile+"</p>";
					html+="<p>区域注册码："+data.agentCode+"</p>";
					html+="<p>企业位置："+data.companyAddr+"</p>";
					
				$('#companyDetail').append(html);
				
				$('#companyDetail').css('display','block');
				
				$('#companyDetailClose').css('display','block');
				
			}
		});
	}
	
	/*点击地图货源详情*/
	function showGoodsDetail(goodsId){
		
		$('#companyDetail').html('');
		
		$.ajax({
			url : $.base+"/center/getGoodsDetail",
			data : {
				"goodsId":goodsId
			},
			async: true,
			type : "post",
			dataType : "json",
			success : function(data) {
				//出发地、目的地、里程数、需求车型、需求车长、重量体积、公司名称、货物名称、货物类型
				var html="<p style='font-size: 15px'>货源详情</p>";
					html+="<p>出发地："+data.startAddr+"</p>";
					html+="<p>目的地："+data.destAddr+"</p>";
					html+="<p>里程数："+data.distance+"公里</p>";
					html+="<p>需求车型："+data.carType+"</p>";
					if(data.isWeightGoods==0){//泡货
						html+="<p>体积："+data.volume+"方</p>";
					}else{//重货
						html+="<p>重量："+data.weight+"吨</p>";
					}
					html+="<p>货物名称："+data.goodsName+"</p>";
					html+="<p>货物类型："+data.goodsType+"</p>";
					html+="<p>预计发货时间："+data.startTime+"</p>";
					html+="<p>预计到达时间："+data.endTime+"</p>";
					
				$('#companyDetail').append(html);
				
				$('#companyDetail').css('display','block');
				
				$('#companyDetailClose').css('display','block');
				
			}
		});
	}
	
	/*点击右区域货源列表--货源详情*/
	function showProGoodDetail(goodsId){
		
		comListClose();
		$('#proGoodDetail').html('');
		
		$.ajax({
			url : $.base+"/center/getGoodsDetail",
			data : {
				"goodsId":goodsId
			},
			async: true,
			type : "post",
			dataType : "json",
			success : function(data) {
				//出发地、目的地、里程数、需求车型、需求车长、重量体积、公司名称、货物名称、货物类型
				var html="<p style='font-size: 15px'>货源详情</p>";
					html+="<p>出发地："+data.startAddr+"</p>";
					html+="<p>目的地："+data.destAddr+"</p>";
					html+="<p>里程数："+data.distance+"公里</p>";
					html+="<p>需求车型："+data.carType+"</p>";
					if(data.isWeightGoods==0){//泡货
						html+="<p>体积："+data.volume+"方</p>";
					}else{//重货
						html+="<p>重量："+data.weight+"吨</p>";
					}
					html+="<p>货物名称："+data.goodsName+"</p>";
					html+="<p>货物类型："+data.goodsType+"</p>";
					html+="<p>预计发货时间："+data.startTime+"</p>";
					html+="<p>预计到达时间："+data.endTime+"</p>";
					
				$('#proGoodDetail').append(html);
				
				$('#proGoodDetail').css('display','block');
				
				$('#proGoodDetailClose').css('display','block');
				
			}
		});
	}
	
	/*订单详情*/
	function showOrderDetail(orderId){
		$('#companyDetail').html('');
		$('#companyDetail').css('display','none');

		$.ajax({
			url : $.base+"/center/getOrderDetail",
			data : {
				"orderId":orderId
			},
			async: true,
			type : "post",
			dataType : "json",
			success : function(data) {
				//当出发地或者目的地为江西的时候才能显示
				var startArr=data.startAddr.toString().substring(0,2);
				var destAddr=data.destAddr.toString().substring(0,2);
				if(startArr!='江西' && destAddr!='江西'){
					return;
				}

				var driverMobile = data.driverMobile.substring(0,data.driverMobile.length-4)+"****";
				//出发地、目的地、里程数、需求车型、需求车长、重量体积、公司名称、货物名称、货物类型
				var html="<p style='font-size: 15px'>订单详情</p>";
				html+="<p>订单编号："+data.orderNum+"</p>";
				html+="<p>企业："+data.comName+"</p>";
				html+="<p>司机："+data.driverName+driverMobile+"</p>";
				html+="<p>出发地："+data.startAddr+"</p>";
				html+="<p>目的地："+data.destAddr+"</p>";
				html+="<p>运距："+data.distance+"公里</p>";
				html+="<p>货物类型："+data.goodsType+"</p>";
				if(data.isWeightGoods==0){//泡货
					html+="<p>体积："+data.volume+"立方</p>";
				}else{//重货
					html+="<p>重量："+data.weight+"吨</p>";
				}
				$('#companyDetail').append(html);
				
				$('#companyDetail').css('display','block');
				
				$('#companyDetailClose').css('display','block');
				
			}
		});
	}
	
	//车辆详情
	function showCarDetail(carId){

		$('#companyDetail').html('');

		$.ajax({
			url : $.base+"/center/showCarDetail",
			data : {
				"carId":carId
			},
			async: true,
			type : "post",
			dataType : "json",
			success : function(data) {
				//司机姓名、司机评价、所在位置、车型、车长、载重
				var html="<p style='font-size: 15px'>司机详情</p>";
				html+="<p>司机姓名："+data.driverName+"</p>";
				/* html+="<p>司机评价："+data.driverName+"</p>"; */
//				if(data.addr!=null){
//					html+="<p>所在位置："+data.addr+"</p>";
//				}
				html+="<p>车型："+data.carType+"</p>";
				if(data.carLength>50)
				{
					html+="<p>车长：50米</p>";
				}else{
					html+="<p>车长："+data.carLength+"米</p>";
				}

				html+="<p>载重："+data.loadWeight+"吨</p>";
				
				$('#companyDetail').append(html);
				
				$('#companyDetail').css('display','block');
				
				$('#companyDetailClose').css('display','block');
				
			}
		});
	}
	
	
	function comListClose(){
		
		$('#companyDetail').html('');
		
		$('#companyDetail').css('display','none');

		$('#companyDetailClose').css('display','none');
		
		$('#companyList').html('');
		
		$('#companyList').css('display','none');

		$('#companyListClose').css('display','none');

		$('#proGoodDetail').html('');

		$('#proGoodDetail').css('display','none');

		$('#proGoodDetailClose').css('display','none');
		
	}
	
	function comDetailClose(){
		
		$('#companyDetail').html('');
		
		$('#companyDetail').css('display','none');
		
		$('#companyDetailClose').css('display','none');
	}
