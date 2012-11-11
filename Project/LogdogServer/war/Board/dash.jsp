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
        	    	 <li class="active"><a href="#">DashBoard</a></li>
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
          <li><a href="#intro"><i class="icon-chevron-right"></i>Introduction</a></li>
          <li><a href="#license"><i class="icon-chevron-right"></i>License</a></li>
          <li><a href="#setting"><i class="icon-chevron-right"></i>System Setting</a></li>
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
									}
									if('ReportRate'==key)
									{
										Daychart.addSeries({data: eval(value)});
									
									}
							} );
						});
					}
				}

			        },
			        title: {
			            text: 'Date Error Report'
			     
			        },
			     
			    });
				
		           });
			//]]>  </script>
              </div>
             
            
            <div class="row-fluid">
            <div class="span6" id="version" style="height: 400px">
<script type='text/javascript'>
//<![CDATA[ 

$(function () {
    var chart= new Highcharts.Chart({
            chart: {
                renderTo: 'version',
                type: 'column'
            },
            title: {
                text: 'Version Error Rate'
            },
            xAxis: {
                categories: ['App 1.0', 'App 2.0', 'App 3.0', 'App 4.0', 'App 5.0']//APP 버 
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
                }
            },
            series: [{
                name: 'OS 2.0',
                data: [5, 3, 4, 7, 2]
            }, {
                name: 'OS 3.0',
                data: [2, 2, 3, 2, 1]
            }, {
                name: 'OS 4.1',
                data: [3, 4, 4, 2, 5]
            }]
    });
   
});
//]]> 
</script>
            </div><!--/span-->
            <div class="span6"  id="classError" style="height: 400px">
<script type='text/javascript'>
//<![CDATA[ 
$(function () {
    var chart;
    $(document).ready(function() {
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'classError',
            	   plotBackgroundColor: null,
              plotBorderWidth: null,
                plotShadow: false
           	 },
            title: {
                text: 'Class Error Report Rate'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        color: '#000000',
                        connectorColor: '#000000',
                        formatter: function() {
                            return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
                       	 }
                   	 }
             	   }
         	  	 },
           	 series: [{
              	 type: 'pie',
           	     name: 'Browser share',
           	     data: [
                    ['Firefox',   45.0],
                    ['IE',       26.8],
                   	 ['Safari',    8.5],
                    ['Opera',     6.2],
                    ['Others',   0.7]
                ]
           	 }]
        });
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
