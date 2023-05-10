package com.cobby.main.activitylog.api.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cobby.main.activitylog.api.service.ActivityLogService;
import com.cobby.main.common.response.BaseResponseBody;

import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/activityLog")
public class ActivityLogController {

	private final ActivityLogService activityLogService;


	// webhook을 통해 받아오는 정보들
	@PostMapping("/webhooks")
	public ResponseEntity<? extends BaseResponseBody> webhookCreate(
		@RequestHeader Map<String, String> headers,
		@RequestBody String payload
	) {
		activityLogService.webhookCreate(headers, payload);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", "감지되었습니다."));
	}

	@GetMapping("/attendance") // 회원 연속 출석 조회
	public ResponseEntity<? extends BaseResponseBody> getactivityLogInfo(
		@RequestHeader("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId) {

		var info = activityLogService.getactivityLogInfo(userId);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", info));
	}

	@GetMapping("/commit") // 회원 연속 출석 조회
	public ResponseEntity<? extends BaseResponseBody> getactivityLogCommit(
		@RequestHeader("userId")
		@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "올바르지 않은 ID 양식입니다.")
		String userId) {

		var info = activityLogService.getactivityLogCommit(userId);

		return ResponseEntity
			.ok()
			.body(new BaseResponseBody<>(200, "OK", info));
	}
}
