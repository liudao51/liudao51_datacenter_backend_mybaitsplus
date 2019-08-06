package com.liudao51.datacenter.core.aop.vaildate;


import com.liudao51.datacenter.common.constant.ErrorCode;
import com.liudao51.datacenter.core.exception.AppException;
import com.liudao51.datacenter.core.protocol.ParamValidInterface;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 参数验证器
 */
@Aspect
@Component
public class RequestParamValidAop {

    /**
     * 切点
     */
    @Pointcut("@annotation(com.liudao51.datacenter.common.annotation.RequestParamValid)")
    public void annotationPointCut() {
    }


    @Around("annotationPointCut()")
    public Object vaild(ProceedingJoinPoint point) throws Throwable {

        Object[] args = point.getArgs();
        if (args.length > 0) {
            // //注解@RequestParamValid表示的方法的参数对象必须实现ParamValidInterface接口
            if (!(args[0] instanceof ParamValidInterface)) {
                throw new AppException(ErrorCode.REQUEST_NEED_IMPLEMENTS_PARAMVALIDINTERFACE_ERROR.toValue(),
                        ErrorCode.REQUEST_NEED_IMPLEMENTS_PARAMVALIDINTERFACE_ERROR.toCode());
            }

            ParamValidInterface target = (ParamValidInterface) args[0]; //只检查方法的第一个参数
            Validator validator = new Validator();
            List<ConstraintViolation> cv = validator.validate(target); //验证对象中包含Oval标签的属性
            if (cv != null && cv.size() > 0) {
                //throw new ConstraintsViolatedException(cv);
                throw new AppException(ErrorCode.REQUEST_PARAM_ERROR.toValue() + "[" + cv.get(0).getMessage() + "]",
                        ErrorCode.REQUEST_PARAM_ERROR.toCode()); //只返回验证结果的第一条数据，并转换为自定义异常
            }
        }

        return point.proceed();
    }
}
