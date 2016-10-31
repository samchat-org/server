import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.samchat.common.beans.manual.json.redis.UserInfoRds;

public class TestSentinels {
	public static void main(String[] args) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enableDefaultTyping(DefaultTyping.NON_FINAL, As.PROPERTY);
		UserInfoRds rds = new UserInfoRds();
		rds.setAddress("adfadfadf");
 		System.out.print(mapper.writeValueAsBytes(rds));
		Object o = mapper.readValue(mapper.writeValueAsBytes(rds), Object.class);
		
		GenericJackson2JsonRedisSerializer se = new GenericJackson2JsonRedisSerializer();
		byte[] ob = se.serialize(rds);
		o = se.deserialize(ob);
	}
	
	public void sendPush() throws Exception{
		SNSMobilePush sample = new SNSMobilePush(sns);
		// TODO: Uncomment the services you wish to use.
		//sample.demoAndroidAppNotification();
		//sample.demoKindleAppNotification();
		//sample.demoAppleAppNotification();
		//sample.demoAppleSandboxAppNotification();
		sample.demoBaiduAppNotification();
		//sample.demoWNSAppNotification();
		//sample.demoMPNSAppNotification();
		                    
	}
}
