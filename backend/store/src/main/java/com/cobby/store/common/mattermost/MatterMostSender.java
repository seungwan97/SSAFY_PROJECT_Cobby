package com.cobby.store.common.mattermost;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.cobby.store.common.mattermost.dto.MatterMostMessageDto.Attachment;
import com.cobby.store.common.mattermost.dto.MatterMostMessageDto.Attachments;
import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MatterMostSender {

	@Value("${notification.mattermost.enabled}")
	private boolean mattermostEnabled;

	@Value("${notification.mattermost.webhook-url}")
	private String webhookUrl;

	private final RestTemplate restTemplate = new RestTemplate();

	private final MatterMostProperties mattermostProperties;


	public void sendMessage(Exception exception, String uri, String params) {
		if(!mattermostEnabled) return;

		try {
			Attachment attachment = Attachment.builder()
				.channel(mattermostProperties.getChannel())
				.authorIcon(mattermostProperties.getAuthorIcon())
				.authorName(mattermostProperties.getAuthorName())
				.color(mattermostProperties.getColor())
				.pretext(mattermostProperties.getPretext())
				.title(mattermostProperties.getTitle())
				.text(mattermostProperties.getText())
				.footer(mattermostProperties.getFooter())
				.build();

			attachment.addExceptionInfo(exception, uri, params);
			Attachments attachments = new Attachments(attachment);
			attachments.addProps(exception);
			String payload = new Gson().toJson(attachments);

			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-type", MediaType.APPLICATION_JSON_VALUE);

			HttpEntity<String> entity = new HttpEntity<>(payload, headers);
			restTemplate.postForEntity(webhookUrl, entity, String.class);

		} catch (Exception e) {
			log.error("ERROR!! Notification Manager : {}", e.getMessage());
		}
	}
}
