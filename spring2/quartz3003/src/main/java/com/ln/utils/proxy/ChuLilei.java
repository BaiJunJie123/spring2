package com.ln.utils.proxy;

/**
 * <P>对类的进行描述</P>
 *
 * @version: v1.0.0
 * @author: baijj
 * @date: 2020-01-31 16:44
 */
public class ChuLilei  implements CeProxy{

    public CeProxy ceProxy;

    public ChuLilei(CeProxy CeProxy){
        this.ceProxy = CeProxy;
    }

    @Override
    public void write() {
        ceProxy.write();
    }
}
