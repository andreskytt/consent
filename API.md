# Content Provider API
## Backround 
The Content Provider API allows for validation of consents and management of services by service providers. 

The API relies on terminology defined in [Electronic Consent Framework v 1.1](http://dla.gov.in/sites/default/files/pdf/MeitY-Consent-Tech-Framework%20v1.1.pdf).

## Assumptions
The API assumes being accessed via a transport layer that
* Authenticates endpoints
* Assures confidentiality of data in flight
* Provides endpoints with access control mechanisms
* Provides non-repudiation mechanisms for transactions

Unless the context provides such functions, the API must not be used.

As opposed to the requests related to [com.proud.egov.consent workflow](#com.proud.egov.consent-workflow-requests), these APIs are aimed at
supporting the UI and providing unsecured metadata about the system itself.

## API endpoints
The API endpoint URLs are structured as follows:
* `api/ui` supports the user interface
* `api/open` provides open data on the system
* `api/dc/` supports the data consumers in their request to confirm the existence of consents
  * `ws` is the SOAP implementation
  * `consents` is the REST one
  

### /api/ui/consents
Returns a list of com.proud.egov.consent objects.

### /api/open/organizations
Returns a list of organizations known to the system. Is likely to be implemented on top of an external 
registry in production cases, thus the need to query it via the UI rather than joining the tables
on the database side. 

`/api/organizations/{id}` returns information on a particular organization. 

### /api/open/services
Returns a list of services known to the system along with the associated human-readable consent text items 
containing the consent text presented to the user.

`/api/services/{id}` return information on a particular service.


### /api/dc/consents
The mapping of [findConsent](#findconsent) to a simple REST API. It accepts the URL structure of `/api/dc/consents/{userID}/{DCID}/{svcID}`
to implement the same functionality as its SOAP counterpart

## /api/dc/ws
### findConsent
The findConsent request answers the question "Has the user given com.proud.egov.consent to a particular data consumer to access a particular 
service?". Given an User identifier, a Data Consumer identifier and a service identifier, the function returns a com.proud.egov.consent object along 
with a signed com.proud.egov.consent container (if a maching com.proud.egov.consent exists) or a Fault object, if it does not.

The com.proud.egov.consent object contains all the relevant information on the com.proud.egov.consent including the exact text the user accepted upon com.proud.egov.consent
creation. In addition, a A BASE64-encoded ASiC-E container with an XML file with com.proud.egov.consent content and their XAdES signature is returned.
This allows service providers to confirm the validity of the Consent Providers's claims and record com.proud.egov.consent information for compliance
purposes. Also, this provides a mechanism for advancing the API without having to have users re-sign their com.proud.egov.consent documents. 

No mechanism is provided to assure that the format of the container content matches the API defined in this document.

## Detailed API specification
```
<?xml version="1.0" encoding="UTF-8" standalone="no"?><wsdl:definitions xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:sch="http://proud.com/egov/com.proud.egov.consent" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://proud.com/egov/com.proud.egov.consent" targetNamespace="http://proud.com/egov/com.proud.egov.consent">
  <wsdl:types>
    <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema" targetNamespace="http://proud.com/egov/com.proud.egov.consent" version="1.0">

  <xs:element name="findConsentRequest" type="tns:findConsentRequest"/>

  <xs:element name="findConsentResponse" type="tns:findConsentResponse"/>

  <xs:complexType name="com.proud.egov.consent">
    <xs:sequence>
      <xs:element name="identifiers" type="tns:identifiers"/>
      <xs:element name="data" type="tns:data"/>
    </xs:sequence>
    <xs:attribute name="expiry" type="xs:dateTime" use="required"/>
    <xs:attribute name="id" type="xs:string" use="required"/>
  </xs:complexType>

  <xs:complexType name="identifiers">
    <xs:sequence>
      <xs:element name="DCID" type="xs:string"/>
      <xs:element name="userID" type="xs:string"/>
      <xs:element name="CCID" type="xs:string"/>
    </xs:sequence>
  </xs:complexType>

  <xs:complexType name="data">
    <xs:sequence>
      <xs:element name="service" type="tns:service"/>
      <xs:element name="humanReadableConsent" type="xs:string"/>
    </xs:sequence>
  </xs:complexType>

  <xs:complexType name="service">
    <xs:sequence>
      <xs:element name="dataProviderID" type="xs:string"/>
      <xs:element name="name" type="xs:string"/>
    </xs:sequence>
    <xs:attribute name="id" type="xs:string" use="required"/>
  </xs:complexType>

  <xs:complexType name="findConsentRequest">
    <xs:sequence>
      <xs:element name="userID" type="xs:string"/>
      <xs:element name="DCID" type="xs:string"/>
      <xs:element name="serviceID" type="xs:string"/>
    </xs:sequence>
  </xs:complexType>

  <xs:complexType name="findConsentResponse">
    <xs:sequence>
      <xs:element name="com.proud.egov.consent" type="tns:com.proud.egov.consent"/>
      <xs:element minOccurs="0" name="signedConsent" type="xs:string"/>
    </xs:sequence>
  </xs:complexType>
</xs:schema>
  </wsdl:types>
  <wsdl:message name="findConsentRequest">
    <wsdl:part element="tns:findConsentRequest" name="findConsentRequest">
    </wsdl:part>
  </wsdl:message>
  <wsdl:message name="findConsentResponse">
    <wsdl:part element="tns:findConsentResponse" name="findConsentResponse">
    </wsdl:part>
  </wsdl:message>
  <wsdl:portType name="ConsentPort">
    <wsdl:operation name="findConsent">
      <wsdl:input message="tns:findConsentRequest" name="findConsentRequest">
    </wsdl:input>
      <wsdl:output message="tns:findConsentResponse" name="findConsentResponse">
    </wsdl:output>
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="ConsentPortSoap11" type="tns:ConsentPort">
    <soap:binding style="document" transport="http://schemas.xmlsoap.org/soap/http"/>
    <wsdl:operation name="findConsent">
      <soap:operation soapAction=""/>
      <wsdl:input name="findConsentRequest">
        <soap:body use="literal"/>
      </wsdl:input>
      <wsdl:output name="findConsentResponse">
        <soap:body use="literal"/>
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="ConsentPortService">
    <wsdl:port binding="tns:ConsentPortSoap11" name="ConsentPortSoap11">
      <soap:address location="http://localhost:8080/api/ws"/>
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>
```

