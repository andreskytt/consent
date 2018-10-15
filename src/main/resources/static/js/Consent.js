"use strict";

function Consent() {

  var self = this;

  self.init = function () {
    loadConsents();
  };

  self._redirect = function (url) {
    window.location = url;
  };

  function loadConsents() {
    $.getJSON('/api/consents.json', function (data) {
      self._createTableRows(data);
    });
  }

  self._createTableRows = function (data) {
    var templateRow = $('table #row-template').html();

    data.forEach(function (consent) {
      var dcid = consent.dataConsumerID;
      console.log("DCID=" + dcid)
      console.log(consent.services)
      consent.services.forEach(function(service){
          var row = $(templateRow);
          row.find('.dpid').text(service.dataProviderID);
          row.find('.dcid').text(this.dataConsumerID);
          row.find('.serviceid').text(service.id);
          $('tbody').append(row);
      }, consent)
    })
  }
}
