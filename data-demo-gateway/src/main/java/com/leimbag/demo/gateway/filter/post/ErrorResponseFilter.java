package com.leimbag.demo.gateway.filter.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leimbag.demo.gateway.bean.response.ResponseMessage;
import com.leimbag.demo.gateway.bean.response.ResponseMessageBody;
import com.leimbag.demo.gateway.constants.CharsetConstants;
import com.leimbag.demo.gateway.constants.GatewayConstants;
import com.leimbag.demo.gateway.exception.GatewayException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class ErrorResponseFilter extends ZuulFilter {

    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 99;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getResponseStatusCode() != HttpServletResponse.SC_OK;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(HttpServletResponse.SC_OK);
        ctx.getResponse().setHeader(HttpHeaders.CONTENT_TYPE, GatewayConstants.DEFAULT_CONTENT_TYPE);
        ctx.getResponse().setCharacterEncoding(CharsetConstants.CHARSET_UTF8);

        Integer errorCode = GatewayConstants.INTERNAL_SERVER_ERROR_CODE;
        String errorMsg = GatewayConstants.INTERNAL_SERVER_ERROR_MESSAGE;


        Integer errorStatusCode = (Integer) ctx.get("error.status_code");
        String errorMessage = (String) ctx.get("error.message");

        Throwable throwable = (Throwable) ctx.remove("throwable");
//        Throwable throwable = ctx.getThrowable();
        if (throwable == null) {
            String message = String.format("gateway error：code:%s, message:%s", errorStatusCode, errorMessage);
            logger.error(message);
        }

        if (throwable instanceof ZuulException) {
            if (throwable.getCause() instanceof GatewayException) {
                errorCode = ((GatewayException) throwable.getCause()).getErrorCode();
                errorMsg = throwable.getCause().getMessage();
            } else {
                logger.error(throwable.getMessage(), throwable);
            }
        }

        ResponseMessage<ResponseMessageBody> responseMessageBodyResponseMessage = new ResponseMessage<>();
        responseMessageBodyResponseMessage.setCode(errorCode);
        responseMessageBodyResponseMessage.setMessage(errorMsg);
        try {
            ctx.setResponseBody(objectMapper.writeValueAsString(responseMessageBodyResponseMessage));
        } catch (JsonProcessingException e) {
            String message = String.format("gateway error: cause: %s", e.getMessage());
            logger.error(message);
        }

        return null;
    }
}
