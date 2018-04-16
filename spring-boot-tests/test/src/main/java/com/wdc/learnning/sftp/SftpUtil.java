package com.wdc.learnning.sftp;

import com.jcraft.jsch.*;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.userauth.password.PasswordUtils;
import net.schmizz.sshj.xfer.FileSystemFile;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.IOException;
import java.security.Security;

public class SftpUtil {
    private static final String USER = "";
    private static final String HOST = "";
    private static final String PASSWORD = "";
    private static final String CHANNEL_STR = "sftp";
    private static final int CONNECT_TIME_OUT = 30000;

    public static void main(String[] args) throws IOException {
//        sshj();
        jsch();
    }

    private static void jsch() {
        JSch jsch = new JSch();
        try {
            Session session = jsch.getSession(USER, HOST);
            session.setPassword(PASSWORD);
            UserInfo userInfo = new UserInfo() {
                @Override
                public String getPassphrase() {
                    System.out.println("getPassphrase");
                    return null;
                }

                @Override
                public String getPassword() {
                    System.out.println("getPassword");
                    return null;
                }

                @Override
                public boolean promptPassword(String s) {
                    System.out.println("promptPassword:" + s);
                    return false;
                }

                @Override
                public boolean promptPassphrase(String s) {
                    System.out.println("promptPassphrase:" + s);
                    return false;
                }

                @Override
                public boolean promptYesNo(String s) {
                    System.out.println("promptYesNo:" + s);
                    return true;
                }

                @Override
                public void showMessage(String s) {
                    System.out.println("showMessage:" + s);
                }
            };

            session.setUserInfo(userInfo);
            session.connect(CONNECT_TIME_OUT);
            Channel channel = session.openChannel(CHANNEL_STR);
            channel.connect();
            ChannelSftp sftp = (ChannelSftp) channel;
            try {
                sftp.get("/tmp/dump.sql", "/tmp");
            } catch (SftpException e) {
                e.printStackTrace();
            }
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    private static void sshj() throws IOException {
        Security.addProvider(new BouncyCastleProvider());
        try (SSHClient ssh = new SSHClient()) {
            ssh.loadKnownHosts();
            ssh.connect(HOST);
            ssh.authPassword(USER, PasswordUtils.createOneOff(PASSWORD.toCharArray()));
            try (SFTPClient sftp = ssh.newSFTPClient()) {
                sftp.get("/tmp/dump.sql", new FileSystemFile("/tmp"));
            }
        }
    }

}
