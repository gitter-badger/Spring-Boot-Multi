package com.highcharts.common.utils;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Properties;

/**
 * sftp链接类
 */
public class SFTPChannel {
    Session session = null;
    Channel channel = null;
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    public ChannelSftp getChannel(Map<String, String> sftpDetails, int timeout) throws JSchException {

        String ftpHost = sftpDetails.get(SFTPConstants.SFTP_REQ_HOST);
        String port = sftpDetails.get(SFTPConstants.SFTP_REQ_PORT);
        String ftpUserName = sftpDetails.get(SFTPConstants.SFTP_REQ_USERNAME);
        String ftpPassword = sftpDetails.get(SFTPConstants.SFTP_REQ_PASSWORD);

        int ftpPort = SFTPConstants.SFTP_DEFAULT_PORT;
        if (port != null && !port.equals("")) {
            ftpPort = Integer.valueOf(port);
        }
        // 创建JSch对象
        JSch jsch = new JSch();
        // 根据用户名，主机ip，端口获取一个Session对象
        session = jsch.getSession(ftpUserName, ftpHost, ftpPort);
        LOG.debug("Session created.");
        if (ftpPassword != null) {
            // 设置密码
            session.setPassword(ftpPassword);
        }
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        // 为Session对象设置properties
        session.setConfig(config);
        // 设置timeout时间
        session.setTimeout(timeout);
        // 通过Session建立链接
        session.connect();
        LOG.debug("Session connected.");
        LOG.debug("Opening Channel.");
        // 打开SFTP通道
        channel = session.openChannel("sftp");
        // 建立SFTP通道的连接
        channel.connect();
        LOG.debug("Connected successfully to ftpHost = " + ftpHost + ",as ftpUserName = " + ftpUserName
                + ", returning: " + channel);
        return (ChannelSftp) channel;
    }
    public void closeChannel() throws Exception {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }
}

