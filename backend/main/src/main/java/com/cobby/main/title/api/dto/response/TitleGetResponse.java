package com.cobby.main.title.api.dto.response;

import com.cobby.main.quest.db.entity.Quest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TitleGetResponse {

    private int titleId;

    private int questId;

    private String name;

    private String explanation;
}
