package com.thomas.netty.protocol.http.xml.codec;

import io.netty.handler.codec.MessageToMessageDecoder;
import org.jibx.runtime.IBindingFactory;

import java.io.StringReader;
import java.nio.charset.Charset;

/**
 * @创建人 thomas_liu
 * @创建时间 2019/1/7 17:20
 * @描述 TODO
 */
public abstract class AbstractHttpXmlDecoder<T> extends MessageToMessageDecoder<T> {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    private IBindingFactory factory;
    private StringReader reader;
    private Class<?> clazz;
    private boolean isPrint;
    private final static String CHARSET_NAME = "UTF-8";
    private final static Charset UTF_8 = Charset.forName(CHARSET_NAME);


    // ===========================================================
    // Constructors
    // ===========================================================


    public AbstractHttpXmlDecoder(Class<?> clazz) {
        this(clazz, false);
    }

    public AbstractHttpXmlDecoder(Class<?> clazz, boolean isPrint) {
        this.clazz = clazz;
        this.isPrint = isPrint;
    }

    // ===========================================================
    // Getter &amp; Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
