appMain.controller("AppController", function ($location) {
        var ctrl = this;
        
        ctrl.init = function () {
                $location.path("/dashboard/");
        };
        
        ctrl.init();
});

