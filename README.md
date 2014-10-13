chart-ms
========

A simple microservice that can generate non-interactive Highcharts.

How it works
------------
Chart-MS works by taking a JSON request like below

    Formatted for readability
    {
      "html": 
      	<div class="container">
      		<div id="test" style='width:1045px;float:left;border-style:solid; border-width:1px;'>
      			<div class="row">
      			<!-- TEST IMAGE only-->
      				<div class="col-md-3"><img style="margin: 15px; "height="55" width="201" src="data:image/png;base64,base64-encoded-image" /></div>
      				<div class="col-md-6 text-center">
      					<div class="heading">
      						<h3>Test Heading</h3>
      						<p>Sub Heading</p>
      					</div>
      				</div>
      			</div>
      			<div class="row">
      				<!--Chart Container -->
      				<div id="chart" class="col-md-12">
      					
      				</div>				
      			</div>
      		</div>",
       "isFragment": true,
       "clippingDiv": "test",
       "useBootstrap" : true,
       "chart" : "{
                          chart: {
                              type: 'bar'
                          },
                          title: {
                              text: 'Fruit Consumption'
                          },
                          xAxis: {
                              categories: ['Apples', 'Bananas', 'Oranges']
                          },
                          yAxis: {
                              title: {
                                  text: 'Fruit eaten'
                              }
                          },
                          series: [{
                              name: 'Jane',
                              data: [1, 0, 4]
                          }, {
                              name: 'John',
                              data: [5, 7, 3]
                          }]
                      }"
    
    }

and generating a HTML document.  That document is then passed to phantomjs which will render the clipping rectangle of the provided clippingDiv attribute and 
returns the image to the caller.