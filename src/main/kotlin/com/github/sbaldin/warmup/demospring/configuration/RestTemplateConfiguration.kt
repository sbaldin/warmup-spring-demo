package com.github.sbaldin.warmup.demospring.configuration

import org.apache.hc.client5.http.classic.HttpClient
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import java.time.Duration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import java.util.function.Function

@Configuration
class RestTemplateConfiguration {

    @Bean
    fun poolingHttpClientConnectionManager(): PoolingHttpClientConnectionManager {
        return PoolingHttpClientConnectionManager()
            .apply {
                defaultMaxPerRoute = 10
                maxTotal = 10
            }
    }

    @Bean
    fun restTemplate(
        connectionManager: PoolingHttpClientConnectionManager,
        restTemplateBuilder: RestTemplateBuilder
    ): RestTemplate {

        return restTemplateBuilder
            .setConnectTimeout(Duration.ofSeconds(60))
            .setReadTimeout(Duration.ofSeconds(60))
            .requestFactory(Function { requestFactory(connectionManager) }).build()
    }

    private fun requestFactory(
        connectionManager: PoolingHttpClientConnectionManager
    ): HttpComponentsClientHttpRequestFactory {
        val builder = HttpClientBuilder.create()
        return HttpComponentsClientHttpRequestFactory(
            builder.setConnectionManager(connectionManager).build() as HttpClient
        )
    }
}
