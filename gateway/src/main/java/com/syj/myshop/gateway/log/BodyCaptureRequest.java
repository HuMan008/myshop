package com.syj.myshop.gateway.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import reactor.core.publisher.Flux;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * create by GYH on 2023/12/2
 */
@Slf4j
public class BodyCaptureRequest extends ServerHttpRequestDecorator {

    private final StringBuilder body = new StringBuilder();

    public BodyCaptureRequest(ServerHttpRequest delegate) {
        super(delegate);
    }

    public Flux<DataBuffer> getBody() {
        return super.getBody().doOnNext(this::capture);
    }

    private void capture(DataBuffer buffer) {
        try (DataBuffer.ByteBufferIterator byteBufferIterator = buffer.readableByteBuffers()) {
            while (byteBufferIterator.hasNext() && this.body.length() < 1024) {
                ByteBuffer bytes = byteBufferIterator.next();
                this.body.append(StandardCharsets.UTF_8.decode(bytes));
            }
        }
    }

    public String getFullBody() {
        return this.body.toString();
    }

}
