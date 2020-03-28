package io.ddd.jexxa.infrastructure.drivingadapter.rest;

import io.ddd.jexxa.applicationcore.domain.valueobject.JexxaValueObject;

/*
* This service is not available via RESTfulRPC because method setSimpleValueObject is available twice 
*/
public class UnsupportedApplicationService
{
    private JexxaValueObject first;
    private JexxaValueObject second;

    public void setSimpleValueObject(JexxaValueObject simpleValueObject)
    {
        this.first = simpleValueObject;
    }
    public void setSimpleValueObject(JexxaValueObject first, JexxaValueObject second)
    {
        this.first = first;
        this.second = second;
    }
}