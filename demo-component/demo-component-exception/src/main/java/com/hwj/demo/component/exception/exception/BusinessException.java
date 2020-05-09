package com.hwj.demo.component.exception.exception;

import com.hwj.demo.component.exception.entity.MessageContext;
import com.hwj.demo.component.exception.enums.CommonEnums;
import com.hwj.demo.component.exception.enums.EnumsInterface;
import org.apache.commons.lang.text.StrSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：业务异常类
 */
public class BusinessException extends RuntimeException{

    private static Logger logger = LoggerFactory.getLogger(BusinessException.class);

    private static final long serialVersionUID = 1L;

    private int exceptionCode;  // 异常编码

    public int getExceptionCode() {
        return exceptionCode;
    }

    /**
     * 自定义异常信息的异常->[throw new BusinessException("xxxx")],默认code为500
     *
     * @param exceptionMessage 错误消息
     */
    public BusinessException(String exceptionMessage){
        super(exceptionMessage);  //调用基类RuntimeException的构造函数
        this.exceptionCode = CommonEnums.BUSINESS_ERROR.getCode();
    }

    /**
     * 不可替换的异常信息
     *
     * @param enums ? extends EnumsInterface
     */
    public BusinessException(Enum<? extends EnumsInterface> enums){
        super(getMessage(enums));
        this.exceptionCode = getCode(enums);
    }

    /**
     * 可替换的异常信息
     *
     * @param enums   ? extends EnumsInterface
     * @param context 线程上下文
     */
    public BusinessException(Enum<? extends EnumsInterface> enums, MessageContext... context) {
        super(buildMessage(enums, context));
        this.exceptionCode = getCode(enums);
    }

    /**
     * 可自己实现异常信息
     *
     * @param enums    ? extends EnumsInterface
     * @param function 可实现内容
     */
    public BusinessException(Enum<? extends EnumsInterface> enums, Function<Enum<? extends EnumsInterface>, String> function) {
        super(function.apply(enums));
        this.exceptionCode = getCode(enums);
    }

    /**
     * 获取枚举code
     *
     * @param enums 枚举类
     * @return code
     */
    private static Integer getCode(Enum<? extends EnumsInterface> enums) {
        EnumsInterface enumsInterface = (EnumsInterface) enums;
        return enumsInterface.getCode();
    }

    /**
     * 获取枚举消息
     *
     * @param enums 枚举类
     * @return message
     */
    private static String getMessage(Enum<? extends EnumsInterface> enums) {
        EnumsInterface enumsInterface = (EnumsInterface) enums;
        return enumsInterface.getMessage();
    }

    /**
     * 错误消息构建
     *
     * @param enums      枚举类key
     * @param contextArr 自行构建上下文信息【k：枚举类需要替换的内容 v：实际值】
     * @return 真实错误信息
     */
    private static String buildMessage(Enum<? extends EnumsInterface> enums, MessageContext[] contextArr) {
        String message = getMessage(enums);
        List<MessageContext> collectList = Arrays.stream(contextArr)
                .filter(context -> message.contains(context.getK()) && !ObjectUtils.isEmpty(context.getV()))
                .collect(Collectors.toList());

        if (contextArr.length != collectList.size()) {
            logger.error("expectation:{}", message);
            logger.error("actual:{}", Arrays.stream(contextArr).map(MessageContext::getK).collect(Collectors.toList()).toString());
            throw new IllegalArgumentException("invoke error,the expected value is not equal to the expected value");
        }

        Map<String, Object> map = new HashMap<>();
        collectList.forEach(context -> map.put(context.getK(), context.getV()));
        // 字符替换 如果map中有对应的key值变量就会被替换  如字符串为"xxx${content}",map中有key为content，那么就会替换为xxx+value
        return new StrSubstitutor(map).replace(message);
    }


}
