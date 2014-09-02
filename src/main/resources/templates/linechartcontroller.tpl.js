var app = angular.module('app', ['nvd3']);

app.controller('ChartController', function($scope) {
    $scope.options = {
        chart: {
            type: "line",
            width: ${options.width},
            height: ${options.height},
            useInteractiveGuideline: false,
            interactive : false,
            callback: function(chart) {
                console.log("rendered=1");
            }
            #if($options.xAxis)
                ,
                xAxis: {
                    axisLabel: $options.xAxis.label;
                }
            #end
            #if($options.yAxis)
                ,
                yAxis: {
                    axisLabel: $options.yAxis.label;
                }
            #end
        }
    };

    $scope.data = [
      #foreach($datum in $data)
        [$datum.x, $datum.y] #if($data.hasNext),#end
      #end
    ]

});