package com.example.springexample.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Objects;

public class TruProxyAPIOfficersResponse {
    @JsonProperty("etag")
    private String etag;

    @JsonProperty("links")
    private Links links;

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("items_per_page")
    private int items_per_page;

    @JsonProperty("items")
    private TruProxyAPIOfficer[] items;

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getItems_per_page() {
        return items_per_page;
    }

    public void setItems_per_page(int items_per_page) {
        this.items_per_page = items_per_page;
    }

    public TruProxyAPIOfficer[] getItems() {
        return items;
    }

    public void setItems(TruProxyAPIOfficer[] items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruProxyAPIOfficersResponse that = (TruProxyAPIOfficersResponse) o;
        return etag == that.etag && items_per_page == that.items_per_page && Objects.equals(links, that.links) && Objects.equals(kind, that.kind) && Arrays.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(etag, links, kind, items_per_page);
        result = 31 * result + Arrays.hashCode(items);
        return result;
    }
}
