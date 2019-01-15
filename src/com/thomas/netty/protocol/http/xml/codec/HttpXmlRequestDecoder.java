package com.thomas.netty.protocol.http.xml.codec;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.List;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;

/**
 * @创建人 thomas_liu
 * @创建时间 2019/1/8 16:11
 * @描述 TODO
 */
public class HttpXmlRequestDecoder {
//        extends AbstractHttpXmlDecoder<FullHttpRequest>
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================


    // ===========================================================
    // Getter &amp; Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    // ===========================================================
    // Methods
    // ===========================================================

//    @Override
//    protected void decode(ChannelHandlerContext chc, FullHttpRequest fhr, List<Object> list) throws Exception {
//         if(!fhr.getDecoderResult().isSuccess()){
//             sendError(chc, BAD_REQUEST);
//             return;
//         }
//         HttpXmlRequest request = new HttpXmlRequest(fhr, decode0(chc, fhr.content()));
//         list.add(request);
//    }
//
//    private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status){
//        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, Unpooled.copiedBuffer("Failure: "+status.toString()+"\r\n", CharsetUtil.UTF_8));
//        response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
//        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
//    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
