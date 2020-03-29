package io.ddd.jexxa.core;

import java.lang.annotation.Annotation;
import java.util.Properties;

import io.ddd.jexxa.core.factory.DrivenAdapterFactory;
import io.ddd.jexxa.core.factory.DrivingAdapterFactory;
import io.ddd.jexxa.core.factory.PortFactory;
import io.ddd.jexxa.infrastructure.drivingadapter.CompositeDrivingAdapter;
import io.ddd.jexxa.infrastructure.drivingadapter.IDrivingAdapter;
import org.apache.commons.lang.Validate;

public class JexxaMain
{
    CompositeDrivingAdapter compositeDrivingAdapter;
    Properties properties;

    DrivingAdapterFactory drivingAdapterFactory;
    DrivenAdapterFactory drivenAdapterFactory;
    PortFactory portFactory;


    public JexxaMain(Properties properties)
    {
        Validate.notNull(properties);
        compositeDrivingAdapter = new CompositeDrivingAdapter();
        this.properties = properties;

        drivingAdapterFactory = new DrivingAdapterFactory();
        drivenAdapterFactory = new DrivenAdapterFactory();
        portFactory = new PortFactory(drivenAdapterFactory);
    }

    public JexxaMain whiteListDrivenAdapterPackage(String packageName)
    {
        drivenAdapterFactory.whiteListPackage(packageName);
        return this;
    }

    public JexxaMain whiteListPortPackage(String packageName)
    {
        portFactory.whiteListPackage(packageName);
        return this;
    }

    public JexxaMain whiteListPackage(String packageName)
    {
        whiteListDrivenAdapterPackage(packageName);
        whiteListPortPackage(packageName);
        return this;
    }


    public void bind(Class<? extends IDrivingAdapter> adapter, Class<?> port) {
        Validate.notNull(adapter);
        Validate.notNull(port);

        var drivingAdapter = drivingAdapterFactory.newInstanceOf(adapter, properties);
        var inboundPort    = portFactory.newInstanceOf(port, properties);
        Validate.notNull(inboundPort);
        drivingAdapter.register(inboundPort);

        compositeDrivingAdapter.add(drivingAdapter);
    }

   
    public void bindToAnnotatedPorts(Class<? extends IDrivingAdapter> adapter, Class<? extends Annotation> portAnnotation) {
        Validate.notNull(adapter);
        Validate.notNull(portAnnotation);

        //Create ports and adapter
        var drivingAdapter = drivingAdapterFactory.newInstanceOf(adapter, properties);

        var portList = portFactory.createPortsBy(portAnnotation, properties);
        portList.forEach(drivingAdapter::register);
        
        compositeDrivingAdapter.add(drivingAdapter);
    }

    public <T> T newInstanceOfPort(Class<T> port)
    {
        return port.cast(portFactory.newInstanceOf(port, properties));
    }


    public void startDrivingAdapters()
    {
        compositeDrivingAdapter.start();
    }

    public void stopDrivingAdapters()
    {
        compositeDrivingAdapter.stop();
    }
}
