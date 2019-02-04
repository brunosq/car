$(function () {

	//  保存jQuery对象
	var $app = $("#app");
	var $companyLists = $("#companyLists");
	var $totalTradeValue = $("#totalTradeValue");
	var $reduceValue = $("#reduceValue");
	var $goodsTypeBox  = $("#goodsTypeBox");
	var $regionBox = $("#regionBox");
	var $regionTop5 = $("#regionTop5");
	var $registerTrend = $("#registerTrend");
	var $companyDetails = $("#companyDetails");     //企业详情
	var $companyAddr = $("#companyAddr");
	var $companyMobile = $("#companyMobile");
	var $companyName = $("#companyName");
	var $agentCode = $("#agentCode");
	var $earth = $("#earth");

	var width = $app.width();
	var height = 162 * width / 384; // 高保真-宽3840,高1620
	$app.height(height);
	//  获取屏幕高度
	var body_height = $("body").height();
	var margin_top = (body_height - height) / 2;
	$app.css("margin-top",margin_top+"px");
	$app.css("background-size","100% 100%");

	//  初始化
	var goodsTypeChart = echarts.init(document.getElementById('goodsTypeBox'));         // 货物类型占比
	var regionChart = echarts.init(document.getElementById('regionBox'));               // 区域分布
	var regionTop5Chart = echarts.init(document.getElementById("regionTop5"));          // 企业分布区域top5
	var registerTrendChart = echarts.init(document.getElementById('registerTrend'));    // 注册增长趋势


	// 显示标题空的坐标轴
	var option1 = {
		title: {
			text: '货物类型占比',
			left: 'center',
			bottom: 15,
			// padding: [0,0,10,0],
			textStyle: {
				color: '#71FFFF',
				fontSize: '15'
			}
		},
		color: ['#04B6FB','#492C78','#3F4BA9','#406EAD','#5DB585','#EBD997','#CD8E84','#A972BB'],
		tooltip: {},
		legend: {
			x : 'right',
			y : 'bottom'
		},
		series: [{
			type: 'pie',
			label: {
				normal: {
					show: false
				},
				emphasis: {
					show: true
				}
			},
		}]
	};
	var option2 = {
		title: {
			text: '区域分布',
			left: 'center',
			bottom: 15,
			// padding: [0,0,10,0],
			textStyle: {
				color: '#71FFFF',
				fontSize: '15'
			}
		},
		color: ['#04B6FB','#492C78','#3F4BA9','#406EAD','#5DB585','#EBD997','#CD8E84','#A972BB'],
		tooltip: {},
		legend: {
			x : 'right',
			y : 'bottom'
		},
		series: [{
			type: 'pie',
			label: {
				normal: {
					show: false
				},
				emphasis: {
					show: true
				}
			},
		}]
	};
	var option3 = {
		title: {
			text: '企业分布区域',
			textStyle: {
				color: '#69F2FF'
			},
			subtext: 'TOP5',
			subtextStyle: {
				color: '#6DF7B3'
			}
		},
		xAxis: {
			show: false
			// type: 'value'
		},
		yAxis: {
			type: 'category',
			data: [1,2,3,4,5],
			splitLine: {
				show: false
			}
		}
	};
	var option4 = {
		title: {
			text: '注册增长趋势',
			textStyle: {
				color: '#69F2FF'
			}
		},
		xAxis: {
			type: 'category',
			boundaryGap: false,
		},
		yAxis: {
			type: 'value',
			splitLine: {
				lineStyle: {
					type: 'dotted',
					color: '#12323F',
					width: 1
				}
			}
		},
		series: [{
			type: 'line',
			smooth: 0.5,  // 平滑显示
			lineStyle: {
				normal: {
					color: '#76FCFF',
				}
			},
			itemStyle: {
				normal: {
					opacity: 0
				}
			},
			areaStyle: {
				normal: {
					color: {
						type: 'linear',
						x: 0,
						y: 0,
						x2: 0,
						y2: 1,
						colorStops: [{
							offset: 0, color: 'rgba(49,107,112,0.9)' // 0% 处的颜色
						}, {
							offset: 1, color: 'rgba(49,107,112,0)' // 100% 处的颜色
						}],
						globalCoord: false // 缺省为 false
					}
				}
			}
		}]
	};
	// 显示loading动画
	var loadingOption = {
		text: '',
		color: '#69F2FF',
		textColor: '#69F2FF',
		maskColor: 'rgba(255, 255, 255, 0)'
	};
	goodsTypeChart.showLoading(loadingOption);
	regionChart.showLoading(loadingOption);
	regionTop5Chart.showLoading(loadingOption);
	registerTrendChart.showLoading(loadingOption);

	//  setOption
	goodsTypeChart.setOption(option1);
	regionChart.setOption(option2);
	regionTop5Chart.setOption(option3);
	registerTrendChart.setOption(option4);

	//  发起ajax请求
	getJSON('/center/getProCompanyList',data001,getProCompanyList);     //请求省份企业列表（全国）
	getJSON('/center/getTotalTradeMoney',"",getTotalTradeMoney);        //全国交易额
	getJSON('/center/getTotalTradeMoney',"",getGoodsType);              //货物类型占比 测试
	getJSON('/center/getTotalTradeMoney',"",getRegion);                 //区域分布 测试
	getJSON('/center/getTotalTradeMoney',"",getRegionTop5);             //企业分布区域top5 测试
	getJSON('/center/getNearMonthTrade',"",getRegisterTrend);          //注册增长趋势 测试


	//  ajax请求回调
	function getProCompanyList(data) {
		// console.log(JSON.stringify(data,null,4));
		// console.log(JSON.stringify(data));
		var html = "";
		$.each(data,function (index,item) {
			html +='<li data-id="'+item.id+'">'+item.companyName+'</li>';
		})
		$companyLists.html(html);
	}

	function getTotalTradeMoney(data) {
		console.log(JSON.stringify(data,null,4));
		$totalTradeValue.html(data.totalTradeMoney);
	}

	function getGoodsType(data) {
		goodsTypeChart.hideLoading();
		var data = [
			{
				"value":"11",
				"name":"动、植物及产品"
			},
			{
				"value":"23",
				"name":"食品"
			},
			{
				"value":"21",
				"name":"金属原料、建材"
			},
			{
				"value":"8",
				"name":"石油、化工原料"
			},
			{
				"value":"20",
				"name":"轻纺、日用品"
			},
			{
				"value":"10",
				"name":"电器、设备"
			}
		];//测试
		var array = [];
		$.each(data,function (index,item) {
			array.push(item.name);
		})
		// 填入数据
		goodsTypeChart.setOption({
			legend: {
				orient:'vertical',  //  图例列表布局朝向
				top: 'middle',
				right: 0,
				itemGap: 2,
				itemWidth: 6,
				itemHeight: 6,
				borderRadius: 6,
				borderWidth: 0,
				textStyle: {
					color: "#70FFFF",
					fontSize: 12
				},
				data:array
			},
			series : [
				{
					name:'货物类型占比',
					radius : [27, '60%'],     //  饼图的半径
					center : ['34%', '47%'],    //  饼图圆心坐标
					roseType : 'radius',
					label: {
						normal: {
							show: false
						},
						emphasis: {
							show: true
						}
					},
					lableLine: {
						normal: {
							show: false
						},
						emphasis: {
							show: true
						}
					},
					data:data
				}
			]
		});
	}

	function  getRegion(data) {
		regionChart.hideLoading();
		var data = [
			{
				"value":"100",
				"name":"华东一区"
			},
			{
				"value":"123",
				"name":"华东二区"
			},
			{
				"value":"210",
				"name":"华中大区"
			},
			{
				"value":"80",
				"name":"华中二区"
			},
			{
				"value":"200",
				"name":"华北大区"
			},
			{
				"value":"108",
				"name":"华北二区"
			},
			{
				"value":"101",
				"name":"华北三区"
			},
			{
				"value":"154",
				"name":"华南二区"
			}
		];//测试
		var array = [];
		$.each(data,function (index,item) {
			array.push(item.name);
		})
		// 填入数据
		regionChart.setOption({
			legend: {
				orient:'vertical',  //  图例列表布局朝向
				top: 'middle',
				right: 0,
				itemGap: 2,
				itemWidth: 6,
				itemHeight: 6,
				borderRadius: 6,
				borderWidth: 0,
				textStyle: {
					color: "#70FFFF",
					fontSize: 12
				},
				data:array
			},
			series : [
				{
					name:'区域分布',
					radius : [27, '60%'],     //  饼图的半径
					center : ['34%', '47%'],    //  饼图圆心坐标
					roseType : 'radius',
					label: {
						normal: {
							show: false
						},
						emphasis: {
							show: true
						}
					},
					lableLine: {
						normal: {
							show: false
						},
						emphasis: {
							show: true
						}
					},
					data:data
				}
			]
		});
	}

	function getRegionTop5(data) {
		regionTop5Chart.hideLoading();
		var data = [
			{
				"value":"500",
				"name":"华东一区"
			},
			{
				"value":"400",
				"name":"华东二区"
			},
			{
				"value":"300",
				"name":"华中大区"
			},
			{
				"value":"200",
				"name":"华中二区"
			},
			{
				"value":"100",
				"name":"华北大区"
			}
		];//测试
		var array = [];
		$.each(data,function (index,item) {
			array.push(item.value);
		});
		array.reverse();
		// 填入数据
		regionTop5Chart.setOption({
			series: [{
			type: 'bar',                                 //  柱状/条形图
				barWidth: 5,
				itemStyle: {
					normal: {
						color: '#4CBBBA',
						barBorderRadius: 4
					}
				},
				data: array
			}]
		});
	}

	function getRegisterTrend(data) {
		registerTrendChart.hideLoading();
		var dateArray = [];
		var valueArrary = [];
		$.each(data,function (index,item) {
			dateArray.push(item.month);
			valueArrary.push(item.trade);
		})
		registerTrendChart.setOption({
			xAxis: {
				data: dateArray
			},
			series: [{
				data: valueArrary
			}]
		});
	}

	function getCompanyDetail(data) {
		console.log(JSON.stringify(data,null,4));
		$companyName.text();    //清空
		$companyMobile.text();
		$agentCode.text();
		$companyAddr.text();
		var companyName = data.companyName;
		var companyMobile = data.companyMobile;
		if (companyMobile != null && companyMobile != undefined && companyMobile != ""){
			companyMobile = companyMobile.substring(0,companyMobile.length-4) + "****";
		}
		var agentCode = data.agentCode;
		var companyAddr = data.companyAddr;
		$companyName.text(companyName);
		$companyMobile.text(companyMobile);
		$agentCode.text(agentCode);
		$companyAddr.text(companyAddr);
	}
	// 左侧列表点击事件
	$companyLists.on('click','li',function () {
		// 展示详情
		$companyDetails.show().siblings().hide();
		var companyId = $(this).attr("data-id");
		data002.companyId = companyId;
		getJSON('/center/getCompanyDetail',data002,getCompanyDetail);   //请求企业详情
	})
})