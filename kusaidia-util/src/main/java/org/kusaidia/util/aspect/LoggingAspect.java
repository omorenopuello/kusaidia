package org.kusaidia.util.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.kusaidia.util.logging.Log;
import org.kusaidia.util.logging.LogLevel;
import org.kusaidia.util.logging.Loggable;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * Logging cross-cutting concern aspect implementation
 *
 * @author Angel L. Villalain Garcia
 * @version 1.0.0
 * @since 1.0.0
 */
@Aspect
@Component
public final class LoggingAspect {
    private static final String BEFORE_STRING = "[ entering < {0} > ]";
    private static final String BEFORE_PARAM_STRING = "[ entering < {0} > " +
            "with " +
            "params {1} ]";
    private static final String AFTER_THROW_PARAM_STRING = "[ exception thrown < " +
            "{0} > exception message {1} with params {2} ]";
    private static final String AFTER_THROW_STRING = "[ exception thrown < {0} > " +
            "exception message {1} ]";
    private static final String RETURN_STRING = "[ leaving < {0} > returning {1} ]";
    private static final String RETURN_VOID_STRING = "[ leaving < {0} > ]";
    private static final String DEFAULT_BEFORE_MESSAGE = "log.before.message";
    private static final String DEFAULT_BEFORE_PARAM_MESSAGE = "log.before" +
            ".param.message";
    private static final String DEFAULT_THROW_MESSAGE = "log.throw.message";
    private static final String DEFAULT_THROW_PARAM_MESSAGE = "log.throw" +
            ".param.message";
    private static final String DEFAULT_RETURNING_MESSAGE = "log.return" +
            ".message";
    private static final String DEFAULT_RETURN_VOID_MESSAGE = "log.void" +
            ".return" +
            ".message";
    /**
     * the {@linkplain Log} implementation used by this aspect
     */
    @Resource
    private Log logger;
    /**
     * the {@linkplain MessageSource} for i18n support containing log messages
     * being used
     */
    @Resource
    private MessageSource messageSource;

    /**
     * Before join point for this aspect to execute before a method annotated
     * with the {@linkplain Loggable} annotation.
     *
     * @param joinPoint the join point this aspect is weaved on.
     * @param loggable the {@linkplain Loggable} annotation.
     */
    @Before(value = "execution(public * * (..)) && @annotation(loggable)",
            argNames = "joinPoint, loggable")
    public void before(JoinPoint joinPoint, Loggable loggable) {
        Class<?> clazz = joinPoint.getTarget().getClass();
        String message = loggable.message();
        Object[] args = new Object[2];
        args[0] = joinPoint.getSignature().getName();

        if (joinPoint.getArgs().length != 0) {
            args[1] = this.constructArgumentsString(joinPoint.getArgs());
            message = (message.isEmpty()) ? LoggingAspect.BEFORE_PARAM_STRING : message;
            this.logger.log(loggable.value(), clazz, null,
                    this.messageSource.getMessage(
                            LoggingAspect.DEFAULT_BEFORE_PARAM_MESSAGE, args,
                            message, Locale.getDefault()));
        } else {
            message = (message.isEmpty()) ? LoggingAspect.BEFORE_STRING : message;
            this.logger.log(loggable.value(), clazz, null,
                    this.messageSource.getMessage(
                            LoggingAspect.DEFAULT_BEFORE_MESSAGE, args,
                            message, Locale.getDefault()));
        }
    }

    /**
     * After throwing join point for this aspect to execute if a method
     * annotatated with the {@linkplain Loggable} annotation throws a
     * {@linkplain Throwable} error.
     *
     * @param joinPoint the join point this aspect is weaved on.
     * @param throwable the {@linkplain Throwable} error.
     */
    @AfterThrowing(value = "execution(public * * (..)) && " +
            "@annotation(org.kusaidia.util.logging.Loggable)",
            throwing = "throwable", argNames = "joinPoint, throwable")
    public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
        Class<?> clazz = joinPoint.getTarget().getClass();
        Object[] args = new Object[3];
        args[0] = joinPoint.getSignature().getName();
        args[1] = throwable.getMessage();
        if (joinPoint.getArgs().length != 0) {
            args[2] = this.constructArgumentsString(joinPoint.getArgs());
            this.logger.log(LogLevel.ERROR, clazz, throwable,
                    this.messageSource.getMessage(
                            LoggingAspect.DEFAULT_THROW_PARAM_MESSAGE, args,
                            LoggingAspect.AFTER_THROW_PARAM_STRING,
                            Locale.getDefault()));
        } else {
            this.logger.log(LogLevel.ERROR, clazz, throwable,
                    this.messageSource.getMessage(
                            LoggingAspect.DEFAULT_THROW_MESSAGE, args,
                            LoggingAspect.AFTER_THROW_STRING,
                            Locale.getDefault()));
        }
    }

    /**
     * After returning join point for this aspect to execute after a method
     * annotated with the {@linkplain Loggable} annotation is executed.
     *
     * @param joinPoint the join point this aspect is weaved on.
     * @param loggable the {@linkplain Loggable} annotation.
     * @param returnValue the value being return by the annotated method.
     */
    @AfterReturning(value = "execution(public * * (..)) && @annotation(loggable)",
            returning = "returnValue", argNames = "joinPoint, " +
            "loggable,returnValue")
    public void afterReturning(JoinPoint joinPoint, Loggable loggable,
                               Object returnValue) {
        Class<? extends Object> clazz = joinPoint.getTarget().getClass();
        String message = loggable.message();
        Object[] args = new Object[2];
        args[0] = joinPoint.getSignature().getName();
        //It will always be method signature since Loggable will always be
        // method based
        Class<?> returnType = ((MethodSignature) joinPoint.getSignature()).
                getReturnType();
        if ("void".compareTo(returnType.getName()) == 0) {
            message = (message.isEmpty()) ?
                    LoggingAspect.RETURN_VOID_STRING : message;
            this.logger.log(loggable.value(), clazz, null,
                    this.messageSource.getMessage(
                            LoggingAspect.DEFAULT_RETURN_VOID_MESSAGE, args,
                            message, Locale.getDefault()));
        } else {
            args[1] = returnValue;
            message = (message.isEmpty()) ? LoggingAspect.RETURN_STRING : message;
            this.logger.log(loggable.value(), clazz, null,
                    this.messageSource.getMessage(
                            LoggingAspect.DEFAULT_RETURNING_MESSAGE, args,
                            message, Locale.getDefault()));
        }
    }

    /**
     * Private helper method to build a {@linkplain String} representation of
     * the array of arguments passed to a given method.
     *
     * @param arguments the array of arguments passed to a given method.
     * @return a {@linkplain String} representation of the array of arguments
     *         passed to a given method.
     */
    private String constructArgumentsString(Object... arguments) {
        StringBuilder buffer = new StringBuilder().append("");
        for (Object object : arguments) {
            buffer.append(object);
        }
        return buffer.toString();
    }
}
