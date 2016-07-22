package com.unidev.templatecore.jmx;

import com.unidev.templatecore.Core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "UniDev:name=CoreMBean")
public class CoreMBean {

    @Autowired
    private Core core;

    @ManagedOperation(description = "Invoke me method")
    @ManagedOperationParameters(
            @ManagedOperationParameter(name = "argument", description = "Variable argument")
    )
    public String invokeMe(String argument) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Argument : ").append(argument).append("\n");
        String invokeMeResult = core.invokeMe();
        stringBuilder.append(invokeMeResult);
        return stringBuilder.toString();
    }

}
