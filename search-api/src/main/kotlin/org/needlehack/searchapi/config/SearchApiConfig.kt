package org.needlehack.searchapi.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.apache.http.HttpHost
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestClientBuilder
import org.elasticsearch.client.RestHighLevelClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary


@Configuration
class SearchApiConfig(@Value("\${jest.elasticsearch.url.schema}") val urlConnectionSchema: String,
                      @Value("\${jest.elasticsearch.url.host}") val urlConnectionHost: String,
                      @Value("\${jest.elasticsearch.url.port}") val urlConnectionPort: Int) {

    @Bean("highLevelclient", destroyMethod = "close")
    fun highLevelclient(restClient : RestClientBuilder): RestHighLevelClient {
        return RestHighLevelClient(restClient)
    }

    @Bean
    fun elasticRestClient() = RestClient.builder(HttpHost(urlConnectionHost, urlConnectionPort, urlConnectionSchema))

    @Bean
    @Primary
    fun jacksonObjectMapper() = ObjectMapper().registerModule(KotlinModule())

}