var webPage = require('webpage');
var page = webPage.create();
var system = require('system');
var args = system.args;
var address, output, size;
var clippingDiv = args[1]
var inFile = args[2]
var outFile= args[3];

page.viewportSize = {
    width: 1920,
    height: 1600
};

/*page.clipRect = {
 top: 0,
 left: 0,
 width: args[1],
 height: args[2]
 }*/

capture = function(targetFile, clipRect) {
    //var clipRect = {top: 0, left:0, width: 40, height: 40};
    if (clipRect) {
        //if (!isType(clipRect, "object")) {
        //    throw new Error("clipRect must be an Object instance.");
        //}
        page.clipRect = clipRect;
        console.log('Capturing page to ' + targetFile + ' with clipRect' + JSON.stringify(clipRect), "debug");
    } else {
        console.log('Capturing page to ' + targetFile, "debug");
    }
    try {
        page.render(targetFile);
    } catch (e) {
        console.log('Failed to capture screenshot as ' + targetFile + ': ' + e, "error");
    }
    return this;
}

captureSelector = function(targetFile, selector) {
    var selector = selector;
    return capture(targetFile, page.evaluate(function(selector) {
        try {
            console.log("Searching document for element: " + selector);
            for(var propertyName in selector) {
                console.log("Property: " + propertyName + "=" + selector[propertyName]);
            }
            var element = document.querySelector(selector.selector);
            if(element == null) {
                throw new Error("Could not find element with name " + selector);
            } else {
                console.log("Found element " + element);
                var clipRect = element.getBoundingClientRect();
                console.log("Clip Rect: " + JSON.stringify(clipRect));
            }
            return {
                top: clipRect.top,
                left: clipRect.left,
                width: clipRect.width,
                height: clipRect.height
            };
        } catch (e) {
            console.log("Unable to fetch bounds for element " + selector, "warning");
        }
    }, { selector: selector }));
}

page.open(inFile, function(status) {
        if(status == 'fail') {
            phantom.exit(1);
        } else {
            setTimeout(function() {
                    var querySelector = "#" + clippingDiv;
                    captureSelector(outFile, querySelector);
                    phantom.exit();
                },
                1000)
        }

    }
);