package com.cy.store.service.ex;

/**业务层异常的基类：throw new ServiceException()
 * 未来的所有业务层异常都可以继承这个基类
 * */
public class ServiceException extends RuntimeException{
    public ServiceException() {
        super();
    } //抛出异常，无信息

    public ServiceException(String message) {
        super(message);
    } //抛出异常信息

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    } //抛出异常的对象+信息

    public ServiceException(Throwable cause) {
        super(cause);
    } //抛出异常的对象

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
