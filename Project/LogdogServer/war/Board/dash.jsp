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
        	    	 <li class="active"><a href="/board/dash.jsp">DashBoard</a></li>
        	    	 <li><a href="#">Setting</a></li>
        	    	 <li><a href="#">About</a></li>
           	   </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

<header class="jumbotron subhead" id="overview">
 	 <div class="container">
    	<h1>DashBoard!!</h1>
    	<p class="lead">LogDog Information and Setting for Starter</p>
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
        </ul>
      </div>
<!--  본 -->
<div class="span8">
              <div class="page-header">
            <h1>Error Info</h1>
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
            
</div>


</div>
    <!-- Footer
    ================================================== -->
    <footer class="footer">
      <div class="container">
        <p class="pull-right"><a href="#">Back to top</a></p>
        <p>Designed and built with all the love in the world by <a href="http://twitter.com/mdo" target="_blank">@mdo</a> and <a href="http://twitter.com/fat" target="_blank">@fat</a>.</p>
        <p>Code licensed under <a href="http://www.apache.org/licenses/LICENSE-2.0" target="_blank">Apache License v2.0</a>, documentation under <a href="http://creativecommons.org/licenses/by/3.0/">CC BY 3.0</a>.</p>
        <p><a href="http://glyphicons.com">Glyphicons Free</a> licensed under <a href="http://creativecommons.org/licenses/by/3.0/">CC BY 3.0</a>.</p>
        <ul class="footer-links">
          <li><a href="http://blog.getbootstrap.com">Blog</a></li>
          <li class="muted">&middot;</li>
          <li><a href="https://github.com/twitter/bootstrap/issues?state=open">Issues</a></li>
          <li class="muted">&middot;</li>
          <li><a href="https://github.com/twitter/bootstrap/wiki">Roadmap and changelog</a></li>
        </ul>
      </div>
    </footer>



   <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
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
 
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
  </body>
  
</html>
