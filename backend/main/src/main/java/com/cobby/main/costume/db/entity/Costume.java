package com.cobby.main.costume.db.entity;

import org.hibernate.annotations.ColumnDefault;

import com.cobby.main.costume.db.entity.enumtype.CostumeCategory;
import com.cobby.main.common.entity.BaseTimeEntity;
import com.cobby.main.quest.db.entity.Quest;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "costume")
@Entity
public class Costume {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "costume_id", nullable = false, columnDefinition = "INT UNSIGNED")
	private Long costumeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@Column(name = "quest_id", nullable = false)
	private Quest quest;

	@Column(name = "name", nullable = false)
	private String name;

	// 유지보수성을 위해 Enum type 을 Ordinal(숫자)이 아닌 String 으로 정의했습니다.
	@Enumerated(EnumType.STRING)
	@Column(name = "category")
	private CostumeCategory category;

	@Column(name = "img_url")
	private String imgUrl;

	@Column(name = "silhouette_img_url")
	private String silhouetteImgUrl;

	@Column(name = "gif_url")
	private String gifUrl;
}
