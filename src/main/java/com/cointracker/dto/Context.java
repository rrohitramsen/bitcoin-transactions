package com.cointracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * HATEOS resource link.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Context {

    private int limit;

    private int offset;

    private List<Link> links;

    public static Context from(int limit, int offset, String address, String endpoint) {
        Context context = Context.builder()
                .limit(limit)
                .offset(offset)
                .links(new ArrayList<>())
                .build();

        if (offset > 0) {
            context.getLinks().add(new Link("prev", String.format(endpoint, address, limit, Math.max(offset - limit, 0))));
        }
        context.getLinks().add(new Link("next", String.format(endpoint, address, limit, offset + limit)));
        return context;
    }
}
