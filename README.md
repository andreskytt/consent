# Consent [![Build Status](https://travis-ci.org/andreskytt/consent.svg?branch=master)](https://travis-ci.org/andreskytt/consent)
A first draft of a consent provider implementation to demonstrate the WSDL and move from architecture to design. See [this document](consent_architecture.pdf)

## Running
`mvn spring-boot:run "-Dlogging.level.root=DEBUG"`

## Testing
After successful launch, `curl http://localhost:8080/ws/consent.wsdl` should yield a WSDL description of the web service. See [here](src/test/java/com/proud/egov/ConsentRepositoryTest.java) for test SOAP requests and expected results
