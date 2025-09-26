package com.sandcoder.rest_api.util;

import brave.propagation.CurrentTraceContext;
import brave.propagation.TraceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppUtils {

    @Autowired
    private CurrentTraceContext currentTraceContext;

    public String getTraceId(){
        TraceContext traceContext = currentTraceContext.get();
        return traceContext != null ? traceContext.traceIdString().concat("-").concat(traceContext.spanIdString()) : null;
    }

}
