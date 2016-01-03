package com.atginfo;

import com.jcraft.jsch.*;

import java.io.File;

/**
 * Created by paustint on 11/4/2015.
 */
public class FtpUtility {

    public static Session getSftpSession(String host, String username, String password, int port) throws JSchException {

        JSch jsch = new JSch();
        Session session = null;

        session = jsch.getSession(username, host, port);
        session.setConfig("StrictHostChecking", "no");
        session.setPassword(password);
        session.connect();
        return session;
    }

    public static void getSftpFile(Session session, String remoteFilename, String localFilename) throws JSchException, SftpException {

        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        sftpChannel.get(remoteFilename, localFilename);
        sftpChannel.exit();
        session.disconnect();
    }

    public static void saveSftpFile(Session session, String remoteFilename, String localFilename) throws JSchException, SftpException {

        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        sftpChannel.put(localFilename, remoteFilename);
        sftpChannel.exit();
        session.disconnect();
    }
}
