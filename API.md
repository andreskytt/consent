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

## Utility and UI APIs
As opposed to the requests related to [consent workflow](#consent-workflow-requests), these APIs are aimed at
supporting the UI and providing unsecured metadata about the system itself.

### /api/consents
Returns a list of consent objects.

### /api/organizations
Returns a list of organizations known to the system. Is likely to be implemented on top of an external 
registry in production cases, thus the need to query it via the UI rather than joining the tables
on the database side. 

`/api/organizations/{id}` returns information on a particular organization 

## Consent workflow requests
### findConsent
The findConsent request answers the question "Has the user given consent to a particular data consumer to access a particular 
service?". Given an User identifier, a Data Consumer identifier and a service identifier, the function returns a consent object along 
with a signed consent container (if a maching consent exists) or a Fault object, if it does not.

The consent object contains all the relevant information on the consent including the exact text the user accepted upon consent
creation. In addition, a A BASE64-encoded ASiC-E container with an XML file with consent content and their XAdES signature is returned.
This allows service providers to confirm the validity of the Consent Providers's claims and record consent information for compliance
purposes. Also, this provides a mechanism for advancing the API without having to have users re-sign their consent documents. 

No mechanism is provided to assure that the format of the container content matches the API defined in this document.

## Detailed API specification
```
<?xml version="1.0" encoding="UTF-8" standalone="no"?><wsdl:definitions xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:sch="http://proud.com/egov/consent" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://proud.com/egov/consent" targetNamespace="http://proud.com/egov/consent">
  <wsdl:types>
    <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema" targetNamespace="http://proud.com/egov/consent" version="1.0">

  <xs:element name="findConsentRequest" type="tns:findConsentRequest"/>

  <xs:element name="findConsentResponse" type="tns:findConsentResponse"/>

  <xs:complexType name="consent">
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
      <xs:element name="consent" type="tns:consent"/>
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

