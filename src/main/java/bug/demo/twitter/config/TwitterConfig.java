package bug.demo.twitter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
public class TwitterConfig {

    @Value("${twitter.oauth.consumerKey}")
    private String consumerKey;

    @Value("${twitter.oauth.consumerSecret}")
    private String consumerSecretKey;

    @Value("${twitter.oauth.accessToken}")
    private String accessToken;

    @Value("${twitter.oauth.accessTokenSecret}")
    private String accessTokenSecret;

    @Bean
    public Twitter twitter() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecretKey)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }

}
