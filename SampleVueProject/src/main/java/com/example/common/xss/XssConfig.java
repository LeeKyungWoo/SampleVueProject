package com.example.common.xss;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.example.common.utils.HTMLCharacterEscapes;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class XssConfig {

	  //이미 기존에 등록된 ObjectMapper Bean이 있다면 JSON 요청/응답에서 사용하기 위해 의존성 주입을 받아 사용한다.
	  private final ObjectMapper objectMapper;

	  @Bean
	  public MappingJackson2HttpMessageConverter jsonEscapeConverter() {
	    ObjectMapper copy = objectMapper.copy();
	    copy.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());
	    return new MappingJackson2HttpMessageConverter(copy);
	  }
	}