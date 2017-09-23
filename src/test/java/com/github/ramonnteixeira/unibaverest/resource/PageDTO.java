package com.github.ramonnteixeira.unibaverest.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public class PageDTO<T> extends PageImpl<T> {

    @JsonCreator
    public PageDTO(
            @JsonProperty("content") List<T> content,
            @JsonProperty("number") int page,
            @JsonProperty("size") int size,
            @JsonProperty("totalElements") Long total) {
        super(content, new PageRequest(page, size), total);
    }

    
}
