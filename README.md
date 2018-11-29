# Consent [![Build Status](https://travis-ci.org/andreskytt/com.proud.egov.consent.svg?branch=master)](https://travis-ci.org/andreskytt/com.proud.egov.consent)
A first draft of a com.proud.egov.consent provider implementation to demonstrate the WSDL and move from architecture to design. See [this document](consent_architecture.pdf) for architecture discussion and [the API docs](API.md) for API specification.

## Running
`mvn spring-boot:run`

## Testing
After successful launch, `curl http://localhost:8080/api/ws/com.proud.egov.consent.wsdl` should yield a WSDL description of the web service. See [here](src/test/java/com/proud/egov/ConsentRepositoryTest.java) for test SOAP requests and expected results
