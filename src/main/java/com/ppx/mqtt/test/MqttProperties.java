package com.ppx.mqtt.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MqttProperties {

    /**
     * 用户名
     */
    @Value("${mqtt.username}")
    private String username;

    /**
     * 密码
     */
    @Value("${mqtt.password}")
    private String password;

    /**
     * 连接地址
     */
    @Value("${mqtt.host-url}")
    private String hostUrl;

    /**
     * 进-客户Id
     */
    @Value("${mqtt.in-client-id}")
    private String inClientId;

    /**
     * 出-客户Id
     */
    @Value("${mqtt.out-client-id}")
    private String outClientId;

    /**
     * 客户Id
     */
    @Value("${mqtt.client-id}")
    private String clientId;

    /**
     * 默认连接话题
     */
    @Value("${mqtt.default-topic}")
    private String defaultTopic;

    /**
     * 超时时间
     */
    @Value("${mqtt.timeout}")
    private int timeout;

    /**
     * 保持连接数
     */
    @Value("${mqtt.keepalive}")
    private int keepalive;

    /**是否清除session*/
    @Value("${mqtt.clearSession}")
    private boolean clearSession;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHostUrl() {
		return hostUrl;
	}

	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}

	public String getInClientId() {
		return inClientId;
	}

	public void setInClientId(String inClientId) {
		this.inClientId = inClientId;
	}

	public String getOutClientId() {
		return outClientId;
	}

	public void setOutClientId(String outClientId) {
		this.outClientId = outClientId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getDefaultTopic() {
		return defaultTopic;
	}

	public void setDefaultTopic(String defaultTopic) {
		this.defaultTopic = defaultTopic;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getKeepalive() {
		return keepalive;
	}

	public void setKeepalive(int keepalive) {
		this.keepalive = keepalive;
	}

	public boolean isClearSession() {
		return clearSession;
	}

	public void setClearSession(boolean clearSession) {
		this.clearSession = clearSession;
	}
    

}
