<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>LogDog -The only choice for your development -</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="/assets/css/bootstrap.css" rel="stylesheet">
    <link href="/assets/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="/assets/css/docs.css" rel="stylesheet">
    <link href="/assets/js/google-code-prettify/prettify.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
    
    <link rel="stylesheet" type="text/css" href="/assets/js/ui.multiselect.css" />
    <link rel="stylesheet" type="text/css" href="/assets/css/ui.jqgrid.css" />

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
	<script type='text/javascript' src='https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js'></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>
    <!-- Fav and touch icons -->
    <link rel="shortcut icon" href="/assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="/assets/ico/apple-touch-icon-57-precomposed.png">
    
<script>
var Request = function() {
    this.getParameter = function( name ) {
        var rtnval = '';
        var nowAddress = unescape(location.href);
        var parameters = (nowAddress.slice(nowAddress.indexOf('?')+1,nowAddress.length)).split('&');
        for(var i = 0 ; i < parameters.length ; i++)
        {
            var varName = parameters[i].split('=')[0];
            if(varName.toUpperCase() == name.toUpperCase())
            {
                rtnval = parameters[i].split('=')[1];
              rtnval = rtnval.split('#')[0];
                break;
            }
        }
        return rtnval;
    };
};

	var Today;
	var AppVersion;
	var OSVersion;
	var ClassName;
	var d = new Date();
	
	var DateCode = (d.getMonth()+1)*100+d.getDate();
	window.onload = function(){
		
		var dayReport = document.getElementById('ToDayList');
		dayReport.onclick = function(){
			var loca = Today.replace(' / ','-');
            
        	location.href='/Report/DayErrorListView.html?Day='+loca;
		};
		
		var VerReport = document.getElementById('VersionError');
		VerReport.onclick = function(){
			location.href='/Report/VersionRateListView.html?AppVer='+AppVersion+'&OSVer='+OSVersion;
		};
		var ClassReport = document.getElementById('ClassError');
		ClassReport.onclick = function(){
			location.href='/Report/ClassRateListView.html?ClassName='+ClassName;
		};
	};
</script>
     <%
     UserService userService = UserServiceFactory.getUserService();
     if (!userService.isUserLoggedIn()) {
  		response.sendRedirect(userService.createLoginURL("../Setting/UserSetting/LogIn"));
    } 
   %>
  </head>
  
  <body data-spy="scroll" data-target=".bs-docs-sidebar">

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
       	   <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>

          <a class="brand" href="#">Logdog</a>
          
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">
             	 <a href="<%=userService.createLogoutURL("../index.jsp")%>" class="navbar-link">logout</a></p>
     	       <ul class="nav">
        	    	 <li class="active"><a href="/Board/dash.jsp">DashBoard</a></li>
        	    	 <li><a href="/Setting/LogdogSetting.html">Setting</a></li>
        	    	 <li><a href="/About/about.html">About</a></li>
           	   </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

<header class="jumbotron subhead" id="overview">
 	 <div class="container" >
 	 	 <div class="row-fluid">
            <div>
    			<img src="/assets/img/logdog/b-144.png" class="span1" align="middle"> <h1>DashBoard</h1>
    		</div>
 
		</div>
		    	<p class="lead">The only choice for your development productivity</p>
 	 </div>
</header>

<div class="container-fluid">
    <!-- Docs nav
    ================================================== -->
    <div class="row-fluid">
      <div class="span3 bs-docs-sidebar">
        <ul class="nav nav-list bs-docs-sidenav">
          <li><a id="ToDayList"><i class="icon-chevron-right"></i>날짜별 에러량</a></li>
          <li><a id="VersionError"><i class="icon-chevron-right"></i>버전별 에러량</a></li>
          <li><a id="ClassError"><i class="icon-chevron-right"></i>클래스별 에러량</a></li>
          <li><a href="#todaylist"><i class="icon-chevron-right"></i>오늘 접수된 에러</a></li>
        </ul>
      </div>
<!--  본 -->
<div class="span8">
              <div class="page-header">
             <img src="/assets/img/logdog/logdog_normal.png" class="span1" align="middle"><h1>Error Statistics</h1>
           </div>

              <div id="day" style="height: 400px">
		<script type='text/javascript'>
		//<![CDATA[ 
		      
		      $(function () {
				 var categori;
		    	 var Daychart = new Highcharts.Chart({
			        chart: {
			            renderTo: 'day',
			                style: {
			                    margin: '0 auto'
			                },
							events: {
								load: function(event) {

								$.getJSON('/Board/summary/Day', function(data) {
									$.each(data,function(key,value){
									if('Day'==key)
									{
										Daychart.xAxis[0].setCategories(eval(value));
										categori=eval(value);
										Today=categori[0];
									}
									if('ReportRate'==key)
									{
										Daychart.addSeries({ name: 'Error Report', data: eval(value)});
										
									}
									} );
									});
								}
							}

			        },
			        yAxis: {
		                min: 0,
		                title: {
		                    text: 'Error Rate'
		                }
			        },
			        title: {
			            text: 'Date Error Report'
			     
			        },
			        plotOptions: {
			            series: {
			                cursor: 'pointer',
			                events: {
			                    click: function(event) {
			            			var nameV = categori[event.point.x];
			            		
			                    	var loca = nameV.replace(' / ','-');
			                
			                    	location.href='/Report/DayErrorListView.html?Day='+loca;
			                    }
			                }
			            }
			        }
			    });
				
		           });
			//]]>  </script>
              </div>
             
            
            <div class="row-fluid">
            <div class="span6" id="version" style="height: 400px">
			<script type='text/javascript'>
				//<![CDATA[ 

				$(function () {
					var AppVer;
    				var chart= new Highcharts.Chart({
           	 			chart: {
                			renderTo: 'version',
                			type: 'column',
							events: {
								load: function(event) {
									$.getJSON('/Board/summary/Version', function(data) {
										$.each(data,function(key,value){
											if('AppVersions'==key)
											{
												chart.xAxis[0].setCategories(eval(value));
												AppVer = eval(value);
												AppVersion =AppVer[0];
											}
											if('OSErrors'==key)
											{
									
												var i = 0;
									
												for(i=0;i<value.length;i++)
													chart.addSeries(eval(value)[i]);
												
												OSVersion=eval(value)[0].name;
											}
										} );
									});
								}	
							}
            			},
          		  		title: {
              				  text: 'Version Error Rate'
           			 	},
            			yAxis: {
                			min: 0,
                			title: {
                    			text: 'App Version Error Rate'
                			},
                			stackLabels: {
                    			enabled: true,
                    			style: {
                        			fontWeight: 'bold',
                        			color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                    			}
                			}
            			},
            			legend: {
               				 align: 'right',
                			x: -100,
                			verticalAlign: 'top',
                			y: 20,
                			floating: true,
               				backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
                			borderColor: '#CCC',
                			borderWidth: 1,
                			shadow: false
            			},
            			tooltip: {
              				  formatter: function() {
                    				return '<b>'+ this.x +'</b><br/>'+
                        			this.series.name +': '+ this.y +'<br/>'+
                        			'Total: '+ this.point.stackTotal;
                				}
			            },
        			    plotOptions: {
                			column: {
                			    stacking: 'normal',
                   				 dataLabels: {
                        			enabled: true,
                    			    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
                   				 }
			                },
	    			        series: {
	                			cursor: 'pointer',
	                			events: {
	                    			click: function(event) {
	                    				
	                    				location.href='/Report/VersionRateListView.html?AppVer='+AppVer[event.point.x]+'&OSVer='+this.name;
	                    				
	                		 	  	 }
	               		 		}	
	         		  	 	}
            			}
				    });
		});//]]> 
				</script>
            </div><!--/span-->
            
            <div class="span6"  id="classError" style="height: 400px">
				<script type='text/javascript'>
				//<![CDATA[ 
					$(function () {
	   				var chart;
     			   	var ClassNameList;
	   				chart = new Highcharts.Chart({
            			chart: {
               				renderTo: 'classError',
            	 	  		plotBackgroundColor: null,
             			 	plotBorderWidth: null,
                			plotShadow: false,
                			events: {
								load: function(event) {

								$.getJSON('/Board/summary/Class', function(data) {
								
									$.each(data,function(key,value){
										
									if('ClassErrors'==key)
									{
										chart.addSeries({
				              				 type: 'pie',
				            	     			name: 'Error Count', data: eval(value)});
										ClassNameList=eval(value);
										ClassName=ClassNameList[0][0];	
										}
								
									} );
									});
								}
							}

           	 			},
            			title: {
                			text: 'Class Error Report Rate'
            			},
        			    plotOptions: {
	    			        series: {
	                			cursor: 'pointer',
	                			events: {
	                    			click: function(event) {
	                    				
	                    				location.href='/Report/ClassRateListView.html?ClassName='+ClassNameList[event.point.x][0];
	                    				
	                		 	  	 }
	               		 		}	
	         		  	 	}
            			}
        			});
   			});
			//]]> </script>
   
            </div><!--/span-->
            </div>
           <section id="todaylist">
            <div class="page-header">
            	 <img src="/assets/img/logdog/logdog_normal.png" class="span1" align="middle"><h1>Today ErrorList</h1>
           </div>  
           	<div class="span12">
           	            
             	<div class="span12" style="vertical-align:middle">
        			<table id="list2" style="vertical-align:middle"></table>
					<div id="pager2"></div>
        			 	<script type="text/javascript">
			 jQuery(
				function()
				{
					
				jQuery("#list2").jqGrid({
				   	url:'/list/errlist/Day='+DateCode,
					datatype: "json",
					autowidth: true,
					height:500,
					jsonReader : { 
						page: "page", 
						total: "total", 
						root: "errors",
						repeatitems: false, 
						id: "key"
					},
				   	colNames:['Error Name', 'Class Name', 'Code Line','Last Updated Day','Total(Weekly)','BugClear','key'],
				   	colModel:[
				   	
				   		{name:'errname',index:'errname' , align:"center" , sortable:false, resizable:false},
				   		{name:'classname',index:'classname' , align:"center" ,sortable:false, resizable:false},
				   		{name:'line',index:'line', align:"center" , sortable:false, fixed:true},
				   		{name:'day',index:'day', sortable:false,resizable:false},		
				   		{name:'total',index:'total',align:"center" , sortable:false, resizable:false},		
				   		{name:'clear',index:'clear',align:"center", sortable:false,resizable:false},
				   		{name:'key',index:'key' , sortable:false, hidden:true},
				   	],		

				    viewrecords: true,
				    ondblClickRow: function(id){ location.href='/DetailView/ErrorDetailView.html?Key='+id;},
				    caption:"Today Error List"
				    
				});
				jQuery("#list2").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});
			}
		);  		
	 </script>
	 	</div>
	 </section>
           	</div>
        	
	

  
  
            
	</div>

</div>

</div>
    <!-- Footer
    ================================================== -->
    <footer class="footer">
      <div class="container">
        <p class="pull-right"><a href="#">Back to top</a></p>
        <p>logdog에 대한 안내는 about에 나와있는 개발자들에게 문의해주시길 바랍니다. 본 프로그램은 라이센스에 위반하지 않는한 무료로 사용가능합니다.<br></p>
        <p>Logdog 프로젝트에서 사용된 라이브러리 코드를 제외한 다른 코드는 MIT라이센스로 모두 공개됩니다.</p>
      </div>
    </footer>



   <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>
 	<script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>
    <script src="/assets/js/jquery.js"></script>
    <script src="/assets/js/google-code-prettify/prettify.js"></script>
    <script src="/assets/js/bootstrap-transition.js"></script>
    <script src="/assets/js/bootstrap-alert.js"></script>
    <script src="/assets/js/bootstrap-modal.js"></script>
    <script src="/assets/js/bootstrap-dropdown.js"></script>
    <script src="/assets/js/bootstrap-scrollspy.js"></script>
    <script src="/assets/js/bootstrap-tab.js"></script>
    <script src="/assets/js/bootstrap-tooltip.js"></script>
    <script src="/assets/js/bootstrap-popover.js"></script>
    <script src="/assets/js/bootstrap-button.js"></script>
    <script src="/assets/js/bootstrap-collapse.js"></script>
    <script src="/assets/js/bootstrap-carousel.js"></script>
    <script src="/assets/js/bootstrap-typeahead.js"></script>
    <script src="/assets/js/bootstrap-affix.js"></script>
    <script src="/assets/js/application.js"></script>
	<script src="/assets/js/ui.multiselect.js" type="text/javascript"></script>
	<script src="/assets/js/jquery.jqGrid.min.js" type="text/javascript"></script>
	<script src="/assets/js/jquery.tablednd.js" type="text/javascript"></script>
	<script src="/assets/js/jquery.contextmenu.js" type="text/javascript"></script>
	<script src="/assets/js/subgrid_grid.js" type="text/javascript"> </script>
	<script src="http://code.highcharts.com/highcharts.js"></script>
	<script src="http://code.highcharts.com/modules/exporting.js"></script>
  </body>
  
</html>
