package com.cointracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * HATEOS resource link.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Link {

    private String rel;

    private String href;
}
