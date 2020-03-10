package com.ln.config.model;

import org.beetl.core.tag.Tag;

import java.io.IOException;
import java.util.Map;

/**
 * <P>对类的进行描述</P>
 *
 * @version: v1.0.0
 * @author: baijj
 * @date: 2020-02-29 22:12
 */
public class Mytag extends Tag {
    @Override
    public void render() {
        String tagName = (String) this.args[0];
        Map attrs = (Map) args[1];
        String s  = attrs.get("attr").toString();
        try {
            this.ctx.byteWriter.writeString(s+"haha");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
