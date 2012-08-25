package org.kusaidia.util.logging.bean;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.kusaidia.util.logging.LogLevel;
import org.kusaidia.util.logging.Loggable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component(value = "simpleBeanSubclass")
public class SimpleBeanSubclass extends SimpleBean {

    private BigDecimal decimalProperty;

    @Loggable(value = LogLevel.TRACE)
    public BigDecimal getDecimalProperty() {
        return decimalProperty;
    }

    @Loggable(value = LogLevel.TRACE)
    public void setDecimalProperty(final BigDecimal decimalProperty) {
        this.decimalProperty = decimalProperty;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("decimalProperty",
                decimalProperty).
                appendSuper(super.toString()).toString();
    }
}
