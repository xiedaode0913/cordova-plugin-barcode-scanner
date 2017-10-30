var cordova = require('cordova'); 
var barcode_scanner_56n = {
    
    scan : function(successCallback ,errorCallback){
        cordova.exec(successCallback, errorCallback, 'Barcode_Scanner_56N', 'scan',[]);
    },
};
module.exports = barcode_scanner_56n;

