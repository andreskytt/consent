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
    $.getJSON('api/ui/consents.json', function (data) {
      self._createTableRows(data);
    });
  }

  function getCompany(id, success){
    $.getJSON('api/public/organizations/' + id, success)
  }

  self._createTableRows = function (data) {
    var templateRow = $('table #row-template').html();

    data.forEach(function (consent) {
      var row = $(templateRow);
      getCompany(consent.service.dataProviderID, function (org){row.find('.dpid').text(org.fullName)})
      getCompany(consent.dataConsumerID, function (org){row.find('.dcid').text(org.fullName)})
      row.find('.serviceid').text(consent.service.name);
      $('tbody').append(row);
    })
  }
}
