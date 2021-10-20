package com.udun.customer.controller;

import com.udun.customer.netty.ChannelUtils;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DemoController {
    @GetMapping("/test")
    public String test(String ctxId, String msg) {
        TextWebSocketFrame tws = new TextWebSocketFrame(msg);
        Channel channel = ChannelUtils.findChannel(ctxId);
        if (channel != null) {
            ChannelUtils.findChannel(ctxId).writeAndFlush(tws);
        } else {
            log.info("通道[{}]不存在", ctxId);
        }
        return msg;
    }
}
