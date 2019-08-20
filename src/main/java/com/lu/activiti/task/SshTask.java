/**
 * 
 */
package com.lu.activiti.task;

import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ClientChannel;
import org.apache.sshd.client.channel.ClientChannelEvent;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.util.io.NoCloseInputStream;
import org.apache.sshd.common.util.io.NoCloseOutputStream;

import lombok.extern.slf4j.Slf4j;

/**
 * @author luxingxiao
 *
 */
@Slf4j
public class SshTask implements IActivitiTask {
	private String login;
	private String host;
	private int port;
	private String password;
	

	@Override
	public void execute() {
		try(SshClient client = SshClient.setUpDefaultClient()) {
		      client.start();

		      try(ClientSession session = client.connect(login, host, port).verify(5000).getSession()) {
		          session.addPasswordIdentity(password);
		          session.auth().verify(5000, TimeUnit.SECONDS);

		          try(ClientChannel channel = session.createChannel(ClientChannel.CHANNEL_SHELL)) {
		              channel.setIn(new NoCloseInputStream(System.in));
		              channel.setOut(new NoCloseOutputStream(System.out));
		              channel.setErr(new NoCloseOutputStream(System.err));
		              channel.open();
		              channel.waitFor(EnumSet.of(ClientChannelEvent.CLOSED), 0L);
		          } finally {
		              session.close(false);
		          }
		    } finally {
		        client.stop();
		    }
		 }catch (Exception e) {
			log.error("SSH connect failed.", e);
		}
	}

}
