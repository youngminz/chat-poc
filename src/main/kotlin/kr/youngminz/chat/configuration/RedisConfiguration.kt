package kr.youngminz.chat.configuration

import kr.youngminz.chat.handler.RedisSubscriber
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfiguration {

    @Bean
    fun redisMessageListenerContainer(
        connectionFactory: RedisConnectionFactory,
        listenerAdapter: MessageListenerAdapter,
        channelTopic: ChannelTopic,
    ): RedisMessageListenerContainer {
        return RedisMessageListenerContainer().also {
            it.setConnectionFactory(connectionFactory)
            it.addMessageListener(listenerAdapter, channelTopic)
        }
    }

    @Bean
    fun listenerAdapter(subscriber: RedisSubscriber): MessageListenerAdapter {
        return MessageListenerAdapter(subscriber, "onMessage")
    }

    @Bean
    fun redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> {
        return RedisTemplate<String, Any>().also {
            it.setConnectionFactory(connectionFactory)
            it.keySerializer = StringRedisSerializer()
            it.valueSerializer = Jackson2JsonRedisSerializer(String::class.java)
        }
    }

    @Bean
    fun channelTopic(): ChannelTopic {
        return ChannelTopic("/sub/chat/room")
    }

}